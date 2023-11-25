package bo.edu.ucb.fithubwelness.bl;

import bo.edu.ucb.fithubwelness.dto.ExerciseDTO;
import bo.edu.ucb.fithubwelness.service.ExerciseDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseBL {

    private final ExerciseDbService exerciseDbService;

    @Autowired
    public ExerciseBL(ExerciseDbService exerciseDbService) {
        this.exerciseDbService = exerciseDbService;
    }

    public List<ExerciseDTO> findAllExercises() {
        return exerciseDbService.getAllExercises();
    }

    public List<ExerciseDTO> findExercisesByBodyPart(String bodyPart) {
        List<ExerciseDTO> allExercises = exerciseDbService.getAllExercises();
        return allExercises.stream()
                .filter(exercise -> exercise.getbodyPart().equalsIgnoreCase(bodyPart))
                .collect(Collectors.toList());
    }
}