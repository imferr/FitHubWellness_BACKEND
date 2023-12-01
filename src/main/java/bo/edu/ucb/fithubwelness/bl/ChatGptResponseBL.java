package bo.edu.ucb.fithubwelness.bl;

import bo.edu.ucb.fithubwelness.dao.ChatGptResponseDAO;
import bo.edu.ucb.fithubwelness.dto.ChatGptResponseDTO;
import bo.edu.ucb.fithubwelness.dto.DailyTrainingDTO;
import bo.edu.ucb.fithubwelness.dto.EvaluationDTO;
import bo.edu.ucb.fithubwelness.dto.GoalDTO;
import bo.edu.ucb.fithubwelness.dto.TypeTrainingDTO;
import bo.edu.ucb.fithubwelness.dto.UserDTO;
import bo.edu.ucb.fithubwelness.entity.ChatGptResponseEntity;
import bo.edu.ucb.fithubwelness.entity.EvaluationEntity;
import bo.edu.ucb.fithubwelness.entity.DailyTrainingEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChatGptResponseBL {

    @Autowired
    private GoalBL goalBL;
    private ChatGptResponseDAO chatGptResponseDAO;
    private RestTemplate restTemplate = new RestTemplate();
    private final String CHATGPT_API_URL = "https://api.openai.com/v1/engines/gpt-4/completions";
    private final String API_KEY = "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";
    private Set<Integer> userFirstInteractionSet = new HashSet<>();

    public ChatGptResponseDTO interactWithChatGpt(UserEntity user, EvaluationEntity evaluation,
            DailyTrainingEntity dailyTraining, String question) {
        String prompt;
        String storedQuestion = "-";

        if (!userFirstInteractionSet.contains(user.getUserId())) {
            prompt = buildWelcomeMessage(user, evaluation);
            userFirstInteractionSet.add(user.getUserId());
        } else {
            prompt = question;
            storedQuestion = question;
        }

        String chatGptResponse = callChatGptApi(prompt);

        return saveChatGptResponse(storedQuestion, chatGptResponse, user, evaluation, dailyTraining);
    }

    private String callChatGptApi(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(API_KEY);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(Map.of("prompt", prompt), headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                CHATGPT_API_URL,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<Map<String, Object>>() {
                });

        Map<String, Object> responseBody = response.getBody();
        return responseBody != null ? (String) responseBody.get("text") : "";
    }

    private String buildWelcomeMessage(UserEntity user, EvaluationEntity evaluation) {
        return "Hola " + user.getName() + ", será un gusto ayudarte con tus preguntas. "
                + "Considerando tu edad de " + calculateAge(user.getBirthday()) + " años, "
                + "tus objetivos de " + getUnfulfilledGoals(user) + ", "
                + "tu peso de " + evaluation.getWeight() + " kg, tu altura de " + evaluation.getHeight() + " cm y "
                + "el estado en que te encuentras según tu índice de masa corporal que es " + evaluation.getState()
                + ".";
    }

    private int calculateAge(Date birthday) {
        LocalDate birthDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    private String getUnfulfilledGoals(UserEntity user) {
        List<GoalDTO> goals = goalBL.findGoalsByUserId(user.getUserId());
        return goals.stream()
                .filter(goal -> !goal.getAccomplished())
                .map(goal -> goal.getTypeGoalId().getTypeGoal())
                .distinct()
                .collect(Collectors.joining(", "));
    }

    private ChatGptResponseDTO saveChatGptResponse(String question, String response, UserEntity user,
            EvaluationEntity evaluation, DailyTrainingEntity dailyTraining) {
        ChatGptResponseEntity entity = new ChatGptResponseEntity();
        entity.setQuestion(question);
        entity.setResponse(response);
        entity.setUserId(user);
        entity.setEvaluationId(evaluation);
        entity.setDailyTrainingId(dailyTraining);
        chatGptResponseDAO.save(entity);

        return convertToDTO(entity);
    }

    private ChatGptResponseDTO convertToDTO(ChatGptResponseEntity entity) {
        ChatGptResponseDTO dto = new ChatGptResponseDTO();
        dto.setChatId(entity.getChatId());
        dto.setQuestion(entity.getQuestion());
        dto.setResponse(entity.getResponse());

        EvaluationDTO evaluationDTO = new EvaluationDTO();
        evaluationDTO.setEvaluationId(entity.getEvaluationId().getEvaluationId());
        evaluationDTO.setWeight(entity.getEvaluationId().getWeight());
        evaluationDTO.setHeight(entity.getEvaluationId().getHeight());
        evaluationDTO.setDate(entity.getEvaluationId().getDate());
        evaluationDTO.setImc(entity.getEvaluationId().getImc());
        evaluationDTO.setState(entity.getEvaluationId().getState());
        dto.setEvaluation(evaluationDTO);

        DailyTrainingDTO dailyTrainingDTO = new DailyTrainingDTO();
        dailyTrainingDTO.setDailyTrainingId(entity.getDailyTrainingId().getDailyTrainingId());
        dailyTrainingDTO.setDate(entity.getDailyTrainingId().getDate());
        TypeTrainingDTO typeTrainingDTO = new TypeTrainingDTO();
        typeTrainingDTO.setTypeTrainingId(entity.getDailyTrainingId().getTypeTrainingId().getTypeTrainingId());
        typeTrainingDTO.setTypeTraining(entity.getDailyTrainingId().getTypeTrainingId().getTypeTraining());
        dailyTrainingDTO.setTypeTrainingId(typeTrainingDTO);
        dto.setDailyTraining(dailyTrainingDTO);

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(entity.getUserId().getUserId());
        userDTO.setName(entity.getUserId().getName());
        userDTO.setEmail(entity.getUserId().getEmail());
        userDTO.setBirthday(entity.getUserId().getBirthday());
        dto.setUserId(userDTO);

        return dto;
    }

}
