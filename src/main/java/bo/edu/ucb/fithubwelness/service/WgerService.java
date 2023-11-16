package bo.edu.ucb.fithubwelness.service;

import bo.edu.ucb.fithubwelness.dto.ExerciseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WgerService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "https://wger.de/api/v2/exerciseinfo/";

    public List<ExerciseDTO> getAllExercises() {
        String url = baseUrl;
        List<ExerciseDTO> exercises = new ArrayList<>();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        if (response != null && response.containsKey("results")) {
            List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");
            for (Map<String, Object> item : results) {
                ExerciseDTO exercise = new ExerciseDTO();
                exercise.setName((String) item.get("name"));
                exercise.setDescription((String) item.get("description"));
                // Obtiene la URL de la primera imagen
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
