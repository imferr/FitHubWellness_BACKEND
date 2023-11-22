package bo.edu.ucb.fithubwelness.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import bo.edu.ucb.fithubwelness.bl.DailyTrainingBL;
import bo.edu.ucb.fithubwelness.dto.DailyTrainingDTO;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/dailytraining")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DailyTrainingAPI {

    private final DailyTrainingBL dailyTrainingBL;
    private static final Logger LOGGER = Logger.getLogger(DailyTrainingAPI.class.getName());

    @Autowired
    public DailyTrainingAPI(DailyTrainingBL dailyTrainingBL) {
        this.dailyTrainingBL = dailyTrainingBL;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDailyTraining(@RequestParam int userId, @RequestParam int typeTrainingId) {
        LOGGER.info("Iniciando el proceso de creación de un entrenamiento");
        try {
            DailyTrainingDTO result = dailyTrainingBL.createDailyTraining(userId, typeTrainingId);
            LOGGER.info("El entrenamiento fue creado con éxito");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al crear el entrenamiento");
            return ResponseEntity.internalServerError().body("Error al crear el entrenamiento: " + e.getMessage());
        } finally {
            LOGGER.info("Finalizando el proceso de creación de un entrenamiento");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findAllDailyTrainingByUserId(@PathVariable int userId) {
        LOGGER.info("Iniciando el proceso de recuperación de los entrenamientos");
        try {
            List<Map<String, Object>> trainings = dailyTrainingBL.findAllDailyTrainingByUserId(userId);
            LOGGER.info("Los entrenamientos fueron recuperados con éxito");
            return ResponseEntity.ok(trainings);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al recuperar los entrenamientos");
            return ResponseEntity.internalServerError().body("Error al recuperar los entrenamientos: " + e.getMessage());
        } finally {
            LOGGER.info("Finalizando el proceso de recuperación de los entrenamientos");
        }
    }
}
