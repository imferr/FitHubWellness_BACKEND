package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.EvaluationBL;
import bo.edu.ucb.fithubwelness.bl.UserBL;
import bo.edu.ucb.fithubwelness.dto.EvaluationDTO;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
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

    @Autowired
    public EvaluationAPI(EvaluationBL evaluationBL, UserBL userBL) {
        this.evaluationBL = evaluationBL;
        this.userBL = userBL;
    }

    @PostMapping("/create")
    public ResponseEntity<EvaluationDTO> createEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        try {
            UserEntity user = userBL.findUserById(evaluationDTO.getUserId().getUserId());
            EvaluationDTO createdEvaluation = evaluationBL.createEvaluation(evaluationDTO, user);
            return ResponseEntity.ok(createdEvaluation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<EvaluationDTO> updateEvaluation(@PathVariable int userId, @RequestBody EvaluationDTO evaluationDTO) {
        try {
            UserEntity user = userBL.findUserById(userId);
            EvaluationDTO updatedEvaluation = evaluationBL.updateEvaluation(evaluationDTO, user);
            return ResponseEntity.ok(updatedEvaluation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
