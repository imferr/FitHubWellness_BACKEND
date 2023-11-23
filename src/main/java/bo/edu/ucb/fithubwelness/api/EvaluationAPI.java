package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.EvaluationBL;
import bo.edu.ucb.fithubwelness.bl.UserBL;
import bo.edu.ucb.fithubwelness.dto.EvaluationDTO;
import bo.edu.ucb.fithubwelness.dto.UserDTO;

import bo.edu.ucb.fithubwelness.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/evaluation")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EvaluationAPI {

    private final EvaluationBL evaluationBL;
    private final UserBL userBL;
    private static final Logger LOGGER = Logger.getLogger(EvaluationAPI.class.getName());

    @Autowired
    public EvaluationAPI(EvaluationBL evaluationBL, UserBL userBL) {
        this.evaluationBL = evaluationBL;
        this.userBL = userBL;
    }

    @PostMapping("/create")
    public ResponseEntity<EvaluationDTO> createEvaluation(@RequestBody Map<String, Object> requestBody) {
        LOGGER.info("Iniciando el proceso de creación de una evaluación");

        try {
            Double weight = (Double) requestBody.get("weight");
            Integer height = (Integer) requestBody.get("height");
            Integer userId = ((Integer) requestBody.get("userId"));

            UserEntity user = userBL.findUserById(userId);
            UserDTO userDto = new UserDTO(user.getUserId(), user.getName(), user.getEmail(), user.getBirthday());

            EvaluationDTO evaluationDTO = new EvaluationDTO();
            evaluationDTO.setWeight(weight);
            evaluationDTO.setHeight(height);
            evaluationDTO.setUserId(userDto);

            EvaluationDTO createdEvaluation = evaluationBL.createEvaluation(evaluationDTO, user);

            LOGGER.info("La evaluación fue creada con éxito");
            return ResponseEntity.ok(createdEvaluation);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al crear la evaluación: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            LOGGER.info("Finalizando el proceso de creación de una evaluación");
        }
    }

    @GetMapping("/last10/{userId}")
    public ResponseEntity<List<EvaluationDTO>> getLast10Evaluations(@PathVariable int userId) {
        LOGGER.info("Iniciando el proceso de obtener las últimas 10 evaluaciones");
        try {
            UserEntity user = userBL.findUserById(userId);
            List<EvaluationDTO> evaluations = evaluationBL.getLast10Evaluations(user);
            LOGGER.info("Las últimas 10 evaluaciones fueron obtenidas con éxito");
            return ResponseEntity.ok(evaluations);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al obtener las últimas 10 evaluaciones");
            return ResponseEntity.internalServerError().build();
        } finally {
            LOGGER.info("Finalizando el proceso de obtener las últimas 10 evaluaciones");
        }
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<EvaluationDTO>> getAllEvaluations(@PathVariable int userId) {
        LOGGER.info("Iniciando el proceso de obtener todas las evaluaciones");
        try {
            UserEntity user = userBL.findUserById(userId);
            List<EvaluationDTO> evaluations = evaluationBL.getAllEvaluations(user);
            LOGGER.info("Todas las evaluaciones fueron obtenidas con éxito");
            return ResponseEntity.ok(evaluations);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al obtener todas las evaluaciones");
            return ResponseEntity.internalServerError().build();
        } finally {
            LOGGER.info("Finalizando el proceso de obtener todas las evaluaciones");
        }
    }
}
