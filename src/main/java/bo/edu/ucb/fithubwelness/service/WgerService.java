package bo.edu.ucb.fithubwelness.service;

import bo.edu.ucb.fithubwelness.dto.ExerciseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WgerService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "https://wger.de/api/v2/exerciseinfo/";

    @Value("${wger.username}")
    private String username;

    @Value("${wger.password}")
    private String password;

    private String jwtToken;

    public WgerService() {
        // Constructor vacío
    }

    private boolean authenticate() {
        try {
            String authUrl = "https://wger.de/api/v2/token/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String authRequest = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
            HttpEntity<String> entity = new HttpEntity<>(authRequest, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(authUrl, entity, Map.class);
            if (response.getBody() != null) {
                jwtToken = (String) response.getBody().get("access");
                return true; // Autenticación exitosa
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }
        return false; // Autenticación fallida
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        if (jwtToken == null || jwtToken.isEmpty()) {
            if (!authenticate()) {
                throw new IllegalStateException("No se pudo autenticar con la API de wger");
            }
        }
        headers.setBearerAuth(jwtToken);
        return headers;
    }

    public List<ExerciseDTO> getAllExercises() {
        HttpHeaders headers = createHeaders();
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<Map> response = restTemplate.exchange(baseUrl, HttpMethod.GET, entity, Map.class);

        List<ExerciseDTO> exercises = new ArrayList<>();
        if (response.getBody() != null && response.getBody().containsKey("results")) {
            List<Map<String, Object>> results = (List<Map<String, Object>>) response.getBody().get("results");
            for (Map<String, Object> item : results) {
                ExerciseDTO exercise = new ExerciseDTO();
                exercise.setName((String) item.get("name"));
                exercise.setDescription((String) item.get("description"));
                List<Map<String, String>> images = (List<Map<String, String>>) item.get("images");
                if (images != null && !images.isEmpty()) {
                    exercise.setLinkPicture(images.get(0).get("image"));
                }
                exercises.add(exercise);
            }
        }
        return exercises;
    }
}
