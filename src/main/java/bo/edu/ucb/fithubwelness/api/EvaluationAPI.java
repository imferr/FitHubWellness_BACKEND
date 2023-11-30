package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.EvaluationBL;
import bo.edu.ucb.fithubwelness.bl.UserBL;
import bo.edu.ucb.fithubwelness.dto.EvaluationDTO;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<EvaluationDTO> createEvaluation(@RequestBody EvaluationDTO evaluationDTO,
            HttpServletRequest request) {
        int userId = evaluationDTO.getUserId().getUserId();
        UserEntity user = userBL.findUserById(userId);
        LOGGER.info("Creando una nueva evaluación para el usuario con ID: " + userId);
        EvaluationDTO createdEvaluation = evaluationBL.createOrUpdateEvaluation(evaluationDTO, user);
        if (createdEvaluation != null) {
            LOGGER.info("Evaluación creada con éxito para el usuario con ID: " + userId);
            return ResponseEntity.ok(createdEvaluation);
        } else {
            LOGGER.warning("Error al crear la evaluación para el usuario con ID: " + userId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<EvaluationDTO> updateEvaluation(@PathVariable int userId,
            @RequestBody EvaluationDTO evaluationDTO, HttpServletRequest request) {
        UserEntity user = userBL.findUserById(userId);
        LOGGER.info("Actualizando la evaluación para el usuario con ID: " + userId);
        EvaluationDTO updatedEvaluation = evaluationBL.createOrUpdateEvaluation(evaluationDTO, user);
        if (updatedEvaluation != null) {
            LOGGER.info("Evaluación actualizada con éxito para el usuario con ID: " + userId);
            return ResponseEntity.ok(updatedEvaluation);
        } else {
            LOGGER.warning("Error al actualizar la evaluación para el usuario con ID: " + userId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<EvaluationDTO> getEvaluationByUserId(@PathVariable int userId) {
        LOGGER.info("Inicio de búsqueda de evaluación por ID de usuario");
        try {
            UserEntity user = userBL.findUserById(userId);
            EvaluationDTO evaluation = evaluationBL.getEvaluationByUser(user);
            if (evaluation == null) {
                LOGGER.info("Evaluación no encontrada para el usuario con ID: " + userId);
                return ResponseEntity.notFound().build();
            }
            LOGGER.info("Evaluación encontrada para el usuario con ID: " + userId);
            return ResponseEntity.ok(evaluation);
        } catch (RuntimeException e) {
            LOGGER.info("Ocurrió un error al buscar la evaluación");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            LOGGER.info("Fin de búsqueda de evaluación por ID de usuario");
        }
    }
}
