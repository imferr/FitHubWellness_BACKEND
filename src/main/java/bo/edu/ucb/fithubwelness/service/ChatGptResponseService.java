package bo.edu.ucb.fithubwelness.service;

import bo.edu.ucb.fithubwelness.bl.GoalBL;
import bo.edu.ucb.fithubwelness.dao.ChatGptResponseDAO;
import bo.edu.ucb.fithubwelness.dto.ChatGptResponseDTO;
import bo.edu.ucb.fithubwelness.dto.DailyTrainingDTO;
import bo.edu.ucb.fithubwelness.dto.EvaluationDTO;
import bo.edu.ucb.fithubwelness.dto.GoalDTO;
import bo.edu.ucb.fithubwelness.dto.TypeTrainingDTO;
import bo.edu.ucb.fithubwelness.dto.UserDTO;
import bo.edu.ucb.fithubwelness.entity.ChatGptResponseEntity;
import bo.edu.ucb.fithubwelness.entity.DailyTrainingEntity;
import bo.edu.ucb.fithubwelness.entity.EvaluationEntity;
import bo.edu.ucb.fithubwelness.entity.TypeTrainingEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;

import java.util.Map;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatGptResponseService {

    @Autowired
    private GoalBL goalBL;
    private ChatGptResponseDAO chatGptResponseDAO;
    private RestTemplate restTemplate = new RestTemplate();

    //TODO: Cambiar API_KEY por la API_KEY de OpenAI
    private final String CHATGPT_API_URL = "https://api.openai.com/v1/engines/text-davinci-003/completions";
    private final String API_KEY = "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";
    private Set<Integer> userFirstInteractionSet = new HashSet<>();

    public ChatGptResponseDTO getChatGptResponse(UserEntity user, EvaluationEntity evaluation,
            DailyTrainingEntity dailyTraining, String question) {
        String prompt;

        // Si es la primera interacción con el usuario, se le da la bienvenida y se le muestra su información
        if (!userFirstInteractionSet.contains(user.getUserId())) {
            prompt = "Hola " + user.getName() + ", será un gusto ayudarte con tus preguntas. "
                    + "Considerando tu edad de " + calculateAge(user.getBirthday()) + " años, "
                    + "tus objetivos de " + getUnfulfilledGoals(user) + ", "
                    + "tu peso de " + evaluation.getWeight() + " kg, tu altura de " + evaluation.getHeight() + " cm y "
                    + "el estado en que te encuentras según tu índice de masa corporal que es " + evaluation.getState()
                    + ".";
            userFirstInteractionSet.add(user.getUserId());
        } else {
            // Si no es la primera interacción, se muestra la pregunta que hizo el usuario anteriormente
            prompt = question;
        }

        HttpHeaders headers = new HttpHeaders();
        // Se agrega el API key de OpenAI
        headers.setBearerAuth(API_KEY);
        // Se agrega el prompt a la petición HTTP que se hará a la API de OpenAI
        // El prompt es la pregunta que hizo el usuario
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(Map.of("prompt", prompt), headers);

        // Se hace la petición HTTP a la API de OpenAI
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                CHATGPT_API_URL,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<Map<String, Object>>() {
                });

                // Se obtiene la respuesta de la API de OpenAI
        Map<String, Object> responseBody = response.getBody();
        String chatGptResponse = responseBody != null ? (String) responseBody.get("text") : "";
        return saveChatGptResponse(chatGptResponse, user, evaluation, dailyTraining);
    }

    // Se guarda la respuesta de la API de OpenAI en la base de datos
    private ChatGptResponseDTO saveChatGptResponse(String response, UserEntity user, EvaluationEntity evaluation,
            DailyTrainingEntity dailyTraining) {
        ChatGptResponseEntity entity = new ChatGptResponseEntity();
        entity.setResponse(response);
        entity.setUserId(user);
        entity.setEvaluationId(evaluation);
        entity.setDailyTrainingId(dailyTraining);
        chatGptResponseDAO.save(entity);

        return convertToDTO(entity);
    }

    // Se obtienen todas las conversaciones que tuvo un usuario con el chatbot 
    private ChatGptResponseDTO convertToDTO(ChatGptResponseEntity entity) {
        return new ChatGptResponseDTO(
                entity.getChatId(),
                entity.getQuestion(),
                entity.getResponse(),
                convertEvaluationToDTO(entity.getEvaluationId()),
                convertDailyTrainingToDTO(entity.getDailyTrainingId()),
                convertUserToDTO(entity.getUserId()));
    }

    private UserDTO convertUserToDTO(UserEntity userEntity) {
        return new UserDTO(
                userEntity.getUserId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getBirthday());
    }

    private EvaluationDTO convertEvaluationToDTO(EvaluationEntity evaluationEntity) {
        return new EvaluationDTO(
                evaluationEntity.getEvaluationId(),
                evaluationEntity.getWeight(),
                evaluationEntity.getHeight(),
                evaluationEntity.getDate(),
                evaluationEntity.getImc(),
                evaluationEntity.getState(),
                convertUserToDTO(evaluationEntity.getUserId()));
    }

    private TypeTrainingDTO convertTypeTrainingToDTO(TypeTrainingEntity typeTrainingEntity) {
        return new TypeTrainingDTO(
                typeTrainingEntity.getTypeTrainingId(),
                typeTrainingEntity.getTypeTraining());
    }

    private DailyTrainingDTO convertDailyTrainingToDTO(DailyTrainingEntity dailyTrainingEntity) {
        return new DailyTrainingDTO(
                dailyTrainingEntity.getDailyTrainingId(),
                dailyTrainingEntity.getDate(),
                convertTypeTrainingToDTO(dailyTrainingEntity.getTypeTrainingId()),
                convertUserToDTO(dailyTrainingEntity.getUserId()));
    }

    private int calculateAge(Date birthday) {
        // Convertir java.sql.Date a java.util.Date
        java.util.Date utilDate = new java.util.Date(birthday.getTime());

        // Convertir java.util.Date a LocalDate
        LocalDate birthDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Calcular la edad
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    // Se obtienen los objetivos que no cumplió el usuario
    private String getUnfulfilledGoals(UserEntity user) {
        List<GoalDTO> goals = goalBL.findGoalsByUserId(user.getUserId());
        return goals.stream()
                .filter(goal -> !goal.getAccomplished())
                .map(goal -> goal.getTypeGoalId().getTypeGoal())
                .distinct()
                .collect(Collectors.joining(", "));
    }

}
