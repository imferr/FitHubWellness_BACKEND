package bo.edu.ucb.fithubwelness.service;

import bo.edu.ucb.fithubwelness.dto.ExerciseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExerciseDbService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "https://exercisedb.p.rapidapi.com/exercises?limit=1300";

    @Value("${exercisedb.api-key}")
    private String apiKey;

    public ExerciseDbService() {
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", apiKey);
        return headers;
    }

    public List<ExerciseDTO> getAllExercises() {
        HttpHeaders headers = createHeaders();
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<List> response = restTemplate.exchange(baseUrl, HttpMethod.GET, entity, List.class);

        List<ExerciseDTO> exercises = new ArrayList<>();
        if (response.getBody() != null) {
            List<Map<String, Object>> results = (List<Map<String, Object>>) response.getBody();
            for (Map<String, Object> item : results) {
                ExerciseDTO exercise = new ExerciseDTO();
                exercise.setName((String) item.get("name"));
                exercise.setLinkPicture((String) item.get("gifUrl"));

                if (item.containsKey("instructions") && item.get("instructions") instanceof List) {
                    List<String> instructions = (List<String>) item.get("instructions");
                    String description = instructions.stream().collect(Collectors.joining(" "));
                    exercise.setDescription(description);
                }

                exercises.add(exercise);
            }
        }
        return exercises;
    }
}
