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
    public ResponseEntity<List<ExerciseDTO>> getAllExercises(
            @RequestParam(required = false) String bodyPart,
            @RequestParam(required = false) String name) {
        LOGGER.info("Iniciando el proceso de obtener ejercicios");
        try {
            List<ExerciseDTO> exercises;
            if (name != null && !name.isEmpty()) {
                exercises = exerciseBL.findExercisesByName(name);
                LOGGER.info("Ejercicios filtrados por nombre obtenidos con éxito");
            } else if (bodyPart != null && !bodyPart.isEmpty()) {
                exercises = exerciseBL.findExercisesByBodyPart(bodyPart);
                LOGGER.info("Ejercicios filtrados por parte del cuerpo obtenidos con éxito");
            } else {
                exercises = exerciseBL.findAllExercises();
                LOGGER.info("Todos los ejercicios obtenidos con éxito");
            }
            return ResponseEntity.ok(exercises);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al obtener los ejercicios: " + e.getMessage() + e.getCause() + e.getClass());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finalizando el proceso de obtener ejercicios");
        }
    }
    
}
