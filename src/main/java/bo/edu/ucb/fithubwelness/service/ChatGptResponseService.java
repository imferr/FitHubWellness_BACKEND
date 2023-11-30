package bo.edu.ucb.fithubwelness.service;

import bo.edu.ucb.fithubwelness.dao.ChatGptResponseDAO;
import bo.edu.ucb.fithubwelness.dto.ChatGptResponseDTO;
import bo.edu.ucb.fithubwelness.dto.DailyTrainingDTO;
import bo.edu.ucb.fithubwelness.dto.EvaluationDTO;
import bo.edu.ucb.fithubwelness.dto.TypeTrainingDTO;
import bo.edu.ucb.fithubwelness.dto.UserDTO;
import bo.edu.ucb.fithubwelness.entity.ChatGptResponseEntity;
import bo.edu.ucb.fithubwelness.entity.DailyTrainingEntity;
import bo.edu.ucb.fithubwelness.entity.EvaluationEntity;
import bo.edu.ucb.fithubwelness.entity.TypeTrainingEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;

import java.util.Map;

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
    private ChatGptResponseDAO chatGptResponseDAO;
    private RestTemplate restTemplate = new RestTemplate();
    private final String CHATGPT_API_URL = "https://api.openai.com/v1/engines/gpt-4/completions";
    private final String API_KEY = "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";

    public ChatGptResponseDTO getChatGptResponse(UserEntity user, EvaluationEntity evaluation,
            DailyTrainingEntity dailyTraining, String question) {
        String prompt = "Prompt basado en la pregunta del usuario: " + question;
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
        String chatGptResponse = responseBody != null ? (String) responseBody.get("text") : "";
        return saveChatGptResponse(chatGptResponse, user, evaluation, dailyTraining);
    }

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

    private ChatGptResponseDTO convertToDTO(ChatGptResponseEntity entity) {
        return new ChatGptResponseDTO(
                entity.getChatId(),
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
}
