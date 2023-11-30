package bo.edu.ucb.fithubwelness.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import bo.edu.ucb.fithubwelness.bl.DailyTrainingBL;
import bo.edu.ucb.fithubwelness.dto.DailyTrainingDTO;

import java.util.Collections;
import java.util.HashMap;
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
    public ResponseEntity<Map<String, Object>> createDailyTraining(@RequestBody Map<String, Object> requestBody) {
        LOGGER.info("Iniciando el proceso de creación de un entrenamiento");
        try {
            int userId = (int) requestBody.get("userId");
            int typeTrainingId = (int) requestBody.get("typeTrainingId");
            DailyTrainingDTO result = dailyTrainingBL.createDailyTraining(userId, typeTrainingId);
            LOGGER.info("El entrenamiento fue creado con éxito");
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("dailyTrainingId", result.getDailyTrainingId());
            responseData.put("date", result.getDate());
            Map<String, Object> typeTrainingData = new HashMap<>();
            typeTrainingData.put("typeTrainingId", result.getTypeTrainingId().getTypeTrainingId());
            typeTrainingData.put("typeTraining", result.getTypeTrainingId().getTypeTraining());
            responseData.put("typeTraining", typeTrainingData);
            responseData.put("userId", userId);
            response.put("data", responseData);
            response.put("message", "Entrenamiento creado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al crear el entrenamiento");
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al crear el entrenamiento");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } finally {
            LOGGER.info("Finalizando el proceso de creación de un entrenamiento");
        }
    }

    @PutMapping("/user/{userId}/update/{dailyTrainingId}")
    public ResponseEntity<Map<String, Object>> updateDailyTraining(
            @PathVariable int userId,
            @PathVariable int dailyTrainingId,
            @RequestBody Map<String, Object> requestBody) {
        LOGGER.info("Iniciando el proceso de actualización de un entrenamiento");
        try {
            int typeTrainingId = (int) requestBody.get("typeTrainingId");
            DailyTrainingDTO result = dailyTrainingBL.updateDailyTraining(userId, dailyTrainingId, typeTrainingId);
            LOGGER.info("El entrenamiento fue actualizado con éxito");
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("dailyTrainingId", result.getDailyTrainingId());
            responseData.put("date", result.getDate());
            Map<String, Object> typeTrainingData = new HashMap<>();
            typeTrainingData.put("typeTrainingId", result.getTypeTrainingId().getTypeTrainingId());
            typeTrainingData.put("typeTraining", result.getTypeTrainingId().getTypeTraining());
            responseData.put("typeTraining", typeTrainingData);
            responseData.put("userId", userId);
            response.put("data", responseData);
            response.put("message", "Entrenamiento actualizado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al actualizar el entrenamiento");
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al actualizar el entrenamiento");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } finally {
            LOGGER.info("Finalizando el proceso de actualización de un entrenamiento");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Map<String, Object>>> findAllDailyTrainingByUserId(@PathVariable int userId) {
        LOGGER.info("Iniciando el proceso de recuperación de los entrenamientos");
        try {
            List<Map<String, Object>> trainings = dailyTrainingBL.findAllDailyTrainingByUserId(userId);
            LOGGER.info("Los entrenamientos fueron recuperados con éxito");
            return ResponseEntity.ok(trainings);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al recuperar los entrenamientos");
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al recuperar los entrenamientos");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList(errorResponse));
        } finally {
            LOGGER.info("Finalizando el proceso de recuperación de los entrenamientos");
        }
    }

}