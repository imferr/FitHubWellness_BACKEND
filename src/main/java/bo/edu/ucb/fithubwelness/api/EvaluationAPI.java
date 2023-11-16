package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.EvaluationBL;
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

    @Autowired
    public EvaluationAPI(EvaluationBL evaluationBL) {
        this.evaluationBL = evaluationBL;
    }

    @PostMapping("/create")
    public ResponseEntity<EvaluationDTO> createEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        UserEntity user = getCurrentUser();
        EvaluationDTO createdEvaluation = evaluationBL.createEvaluation(evaluationDTO.getWeight(), evaluationDTO.getHeight(), user);
        return ResponseEntity.ok(createdEvaluation);
    }

    @GetMapping()
    public ResponseEntity<List<EvaluationDTO>> getAllEvaluations() {
        List<EvaluationDTO> evaluations = evaluationBL.getAllEvaluations();
        return ResponseEntity.ok(evaluations);
    }

    private UserEntity getCurrentUser() {
        return new UserEntity();
    }
}
