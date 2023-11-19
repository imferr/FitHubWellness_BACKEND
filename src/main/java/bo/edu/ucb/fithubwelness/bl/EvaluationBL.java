package bo.edu.ucb.fithubwelness.bl;

import bo.edu.ucb.fithubwelness.dao.EvaluationDAO;
import bo.edu.ucb.fithubwelness.dto.EvaluationDTO;
import bo.edu.ucb.fithubwelness.entity.EvaluationEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationBL {

    private final EvaluationDAO evaluationDAO;

    @Autowired
    public EvaluationBL(EvaluationDAO evaluationDAO) {
        this.evaluationDAO = evaluationDAO;
    }

    public EvaluationDTO createEvaluation(EvaluationDTO evaluationDTO, UserEntity user) {
        EvaluationEntity entity = new EvaluationEntity();
        entity.setWeight(evaluationDTO.getWeight());
        entity.setHeight(evaluationDTO.getHeight());
        entity.setDate(new java.sql.Date(System.currentTimeMillis()));
        entity.setImc(calculateIMC(evaluationDTO.getWeight(), evaluationDTO.getHeight()));
        entity.setState(determineState(entity.getImc()));
        entity.setUserId(user);

        entity = evaluationDAO.save(entity);
        return convertToDTO(entity);
    }

    public List<EvaluationDTO> getLast10Evaluations(UserEntity user) {
        return evaluationDAO.findTop10ByUserIdOrderByDateDesc(user).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<EvaluationDTO> getAllEvaluations(UserEntity user) {
        return evaluationDAO.findAllByUserId(user).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private double calculateIMC(double weight, int height) {
        return weight / Math.pow(height / 100.0, 2);
    }

    private String determineState(double imc) {
        if (imc < 18.5) return "Bajo peso";
        else if (imc <= 24.9) return "Ideal";
        else if (imc <= 29.9) return "Sobrepeso";
        else return "Obesidad";
    }

    private EvaluationDTO convertToDTO(EvaluationEntity entity) {
        return new EvaluationDTO(
                entity.getEvaluationId(),
                entity.getWeight(),
                entity.getHeight(),
                entity.getDate(),
                entity.getImc(),
                entity.getState(),
                null
        );
    }
}
