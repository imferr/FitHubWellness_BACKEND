package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.ExerciseBL;
import bo.edu.ucb.fithubwelness.dto.ExerciseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/exercise")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExerciseAPI {

    private final ExerciseBL exerciseBL;
    private static final Logger LOGGER = Logger.getLogger(ExerciseAPI.class.getName());

    @Autowired
    public ExerciseAPI(ExerciseBL exerciseBL) {
        this.exerciseBL = exerciseBL;
    }

    @GetMapping()
    public ResponseEntity<List<ExerciseDTO>> getAllExercises() {
        LOGGER.info("Iniciando el proceso de obtener todos los ejercicios");
        try {
            List<ExerciseDTO> exercises = exerciseBL.findAllExercises();
            return ResponseEntity.ok(exercises);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al obtener los ejercicios");
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finalizando el proceso de obtener todos los ejercicios");
        }
    }
}
