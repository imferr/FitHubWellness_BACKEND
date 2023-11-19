package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.EvaluationBL;
import bo.edu.ucb.fithubwelness.bl.UserBL;
import bo.edu.ucb.fithubwelness.dto.EvaluationDTO;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluation")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EvaluationAPI {

    private final EvaluationBL evaluationBL;
    private final UserBL userBL;

    @Autowired
    public EvaluationAPI(EvaluationBL evaluationBL, UserBL userBL) {
        this.evaluationBL = evaluationBL;
        this.userBL = userBL;
    }

    @PostMapping("/")
    public ResponseEntity<EvaluationDTO> createEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        try {
            UserEntity user = userBL.findUserById(evaluationDTO.getUserId().getUserId());
            EvaluationDTO createdEvaluation = evaluationBL.createEvaluation(evaluationDTO, user);
            return ResponseEntity.ok(createdEvaluation);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/last10/{userId}")
    public ResponseEntity<List<EvaluationDTO>> getLast10Evaluations(@PathVariable int userId) {
        try {
            UserEntity user = userBL.findUserById(userId);
            List<EvaluationDTO> evaluations = evaluationBL.getLast10Evaluations(user);
            return ResponseEntity.ok(evaluations);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<EvaluationDTO>> getAllEvaluations(@PathVariable int userId) {
        try {
            UserEntity user = userBL.findUserById(userId);
            List<EvaluationDTO> evaluations = evaluationBL.getAllEvaluations(user);
            return ResponseEntity.ok(evaluations);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
