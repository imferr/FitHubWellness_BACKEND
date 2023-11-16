package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.ExerciseBL;
import bo.edu.ucb.fithubwelness.dto.ExerciseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExerciseAPI {

    private final ExerciseBL exerciseBL;

    @Autowired
    public ExerciseAPI(ExerciseBL exerciseBL) {
        this.exerciseBL = exerciseBL;
    }

    @GetMapping()
    public ResponseEntity<List<ExerciseDTO>> getAllExercises() {
        return ResponseEntity.ok(exerciseBL.findAllExercises());
    }
}
