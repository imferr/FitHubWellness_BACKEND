package bo.edu.ucb.fithubwelness.bl;

import bo.edu.ucb.fithubwelness.dao.EvaluationDAO;
import bo.edu.ucb.fithubwelness.dao.EvaluationHistoryDAO;
import bo.edu.ucb.fithubwelness.dto.EvaluationDTO;
import bo.edu.ucb.fithubwelness.entity.EvaluationEntity;
import bo.edu.ucb.fithubwelness.entity.EvaluationHistoryEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

@Service
public class EvaluationBL {

    private final EvaluationDAO evaluationDAO;
    private final EvaluationHistoryDAO evaluationHistoryDAO;

    @Autowired
    public EvaluationBL(EvaluationDAO evaluationDAO, EvaluationHistoryDAO evaluationHistoryDAO) {
        this.evaluationDAO = evaluationDAO;
        this.evaluationHistoryDAO = evaluationHistoryDAO;
    }

    public EvaluationDTO createEvaluation(EvaluationDTO evaluationDTO, UserEntity user) {
        if (evaluationDAO.findByUserId(user) != null) {
            throw new RuntimeException("Ya existe una evaluación para este usuario. Por favor, actualiza la evaluación existente.");
        }
        EvaluationEntity entity = new EvaluationEntity();
        entity.setWeight(evaluationDTO.getWeight());
        entity.setHeight(evaluationDTO.getHeight());
        entity.setDate(new java.sql.Date(System.currentTimeMillis()));
        entity.setImc(calculateIMC(evaluationDTO.getWeight(), evaluationDTO.getHeight()));
        entity.setState(determineState(entity.getImc()));
        entity.setUserId(user);
        entity = evaluationDAO.save(entity);
        EvaluationHistoryEntity historyEntity = new EvaluationHistoryEntity();
        historyEntity.setEvaluationId(entity.getEvaluationId());
        historyEntity.setWeight(entity.getWeight());
        historyEntity.setHeight(entity.getHeight());
        historyEntity.setDate(entity.getDate());
        historyEntity.setImc(entity.getImc());
        historyEntity.setState(entity.getState());
        historyEntity.setUserId(entity.getUserId());
        historyEntity.setValidFrom(new Timestamp(System.currentTimeMillis()));
        historyEntity.setValidTo(null);
        historyEntity.setActive(true);
        evaluationHistoryDAO.save(historyEntity);
        return convertToDTO(entity);
    }

    public EvaluationDTO updateEvaluation(EvaluationDTO evaluationDTO, UserEntity user) {
        EvaluationEntity existingEvaluation = evaluationDAO.findByUserId(user);
        if (existingEvaluation == null) {
            throw new RuntimeException("No se encontró una evaluación para actualizar.");
        }
        existingEvaluation.setWeight(evaluationDTO.getWeight());
        existingEvaluation.setHeight(evaluationDTO.getHeight());
        existingEvaluation.setImc(calculateIMC(evaluationDTO.getWeight(), evaluationDTO.getHeight()));
        existingEvaluation.setState(determineState(existingEvaluation.getImc()));
        evaluationDAO.save(existingEvaluation);
        updateEvaluationHistory(existingEvaluation);
        return convertToDTO(existingEvaluation);
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

    private void updateEvaluationHistory(EvaluationEntity evaluationEntity) {
        EvaluationHistoryEntity historyEntity = new EvaluationHistoryEntity();
        historyEntity.setEvaluationId(evaluationEntity.getEvaluationId());
        historyEntity.setWeight(evaluationEntity.getWeight());
        historyEntity.setHeight(evaluationEntity.getHeight());
        historyEntity.setDate(evaluationEntity.getDate());
        historyEntity.setImc(evaluationEntity.getImc());
        historyEntity.setState(evaluationEntity.getState());
        historyEntity.setUserId(evaluationEntity.getUserId());
        historyEntity.setValidFrom(new Timestamp(System.currentTimeMillis()));
        historyEntity.setValidTo(null);
        historyEntity.setActive(true);
        evaluationHistoryDAO.save(historyEntity);
    }
}
