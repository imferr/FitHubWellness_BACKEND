package bo.edu.ucb.fithubwelness.bl;

import bo.edu.ucb.fithubwelness.dao.EvaluationDAO;
import bo.edu.ucb.fithubwelness.dao.EvaluationHistoryDAO;
import bo.edu.ucb.fithubwelness.dao.LastEvaluationDAO;
import bo.edu.ucb.fithubwelness.dto.EvaluationDTO;
import bo.edu.ucb.fithubwelness.dto.GoalDTO;
import bo.edu.ucb.fithubwelness.entity.EvaluationEntity;
import bo.edu.ucb.fithubwelness.entity.EvaluationHistoryEntity;
import bo.edu.ucb.fithubwelness.entity.LastEvaluationEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;

@Service
public class EvaluationBL {

    private final EvaluationDAO evaluationDAO;
    private final GoalBL goalBL;
    private final EvaluationHistoryDAO evaluationHistoryDAO;
    private final LastEvaluationDAO lastEvaluationDAO;

    @Autowired
    public EvaluationBL(EvaluationDAO evaluationDAO, GoalBL goalBL, EvaluationHistoryDAO evaluationHistoryDAO,
            LastEvaluationDAO lastEvaluationDAO) {
        this.evaluationDAO = evaluationDAO;
        this.goalBL = goalBL;
        this.evaluationHistoryDAO = evaluationHistoryDAO;
        this.lastEvaluationDAO = lastEvaluationDAO;
    }

    public EvaluationDTO createOrUpdateEvaluation(EvaluationDTO evaluationDTO, UserEntity user, String host) {
        EvaluationEntity existingEvaluation = evaluationDAO.findByUserId(user);
        boolean isUpdate = existingEvaluation != null;
        if (isUpdate) {
            updateEvaluationData(existingEvaluation, evaluationDTO, user);
        } else {
            existingEvaluation = createNewEvaluation(evaluationDTO, user);
        }
        updateEvaluationHistory(existingEvaluation, host, user.getUserId());
        updateLastEvaluation(existingEvaluation, user, isUpdate);
        return convertToDTO(existingEvaluation);
    }

    private EvaluationEntity createNewEvaluation(EvaluationDTO evaluationDTO, UserEntity user) {
        EvaluationEntity entity = new EvaluationEntity();
        entity.setWeight(evaluationDTO.getWeight());
        entity.setHeight(evaluationDTO.getHeight());
        entity.setDate(new java.sql.Date(System.currentTimeMillis()));
        entity.setImc(calculateIMC(evaluationDTO.getWeight(), evaluationDTO.getHeight()));
        entity.setState(determineState(entity.getImc()));
        entity.setUserId(user);
        return evaluationDAO.save(entity);
    }

    private void updateEvaluationData(EvaluationEntity existingEvaluation, EvaluationDTO evaluationDTO,
            UserEntity user) {
        existingEvaluation.setWeight(evaluationDTO.getWeight());
        existingEvaluation.setHeight(evaluationDTO.getHeight());
        existingEvaluation.setImc(calculateIMC(evaluationDTO.getWeight(), evaluationDTO.getHeight()));
        existingEvaluation.setState(determineState(existingEvaluation.getImc()));
        evaluationDAO.save(existingEvaluation);
    }

    private void updateLastEvaluation(EvaluationEntity evaluation, UserEntity user, boolean isUpdate) {
        GoalDTO activeGoal = findActiveType3Goal(user.getUserId());
        LastEvaluationEntity lastEvaluation = lastEvaluationDAO.findByUserId(user);

        if (lastEvaluation == null) {
            lastEvaluation = new LastEvaluationEntity();
            lastEvaluation.setUserId(user);
            lastEvaluation.setWeight(evaluation.getWeight());
            lastEvaluationDAO.save(lastEvaluation);
            return;
        }
        if (activeGoal != null && !activeGoal.getAccomplished()) {
            if (evaluation.getWeight() <= (lastEvaluation.getWeight() - activeGoal.getQuantity())) {
                activeGoal.setAccomplished(true);
                goalBL.updateGoal(activeGoal);
                lastEvaluation.setWeight(evaluation.getWeight());
                lastEvaluationDAO.save(lastEvaluation);
            }
        } else {
            lastEvaluation.setWeight(evaluation.getWeight());
            lastEvaluationDAO.save(lastEvaluation);
        }
    }

    private double calculateIMC(double weight, int height) {
        return weight / Math.pow(height / 100.0, 2);
    }

    private String determineState(double imc) {
        if (imc < 18.5)
            return "Bajo peso";
        else if (imc <= 24.9)
            return "Ideal";
        else if (imc <= 29.9)
            return "Sobrepeso";
        else
            return "Obesidad";
    }

    private EvaluationDTO convertToDTO(EvaluationEntity entity) {
        return new EvaluationDTO(
                entity.getEvaluationId(),
                entity.getWeight(),
                entity.getHeight(),
                entity.getDate(),
                entity.getImc(),
                entity.getState(),
                null);
    }

    private void updateEvaluationHistory(EvaluationEntity evaluationEntity, String host, Integer userId) {
        EvaluationHistoryEntity historyEntity = new EvaluationHistoryEntity();
        historyEntity.setWeight(evaluationEntity.getWeight());
        historyEntity.setHeight(evaluationEntity.getHeight());
        historyEntity.setDate(evaluationEntity.getDate());
        historyEntity.setImc(evaluationEntity.getImc());
        historyEntity.setState(evaluationEntity.getState());
        historyEntity.setUserId(evaluationEntity.getUserId());
        historyEntity.setAudDate(new Timestamp(System.currentTimeMillis()));
        historyEntity.setAudHost(host);
        historyEntity.setAudUser(userId);
        evaluationHistoryDAO.save(historyEntity);
    }

    private GoalDTO findActiveType3Goal(int userId) {
        List<GoalDTO> goals = goalBL.findGoalsByUserId(userId);
        for (GoalDTO goal : goals) {
            if (goal.getTypeGoalId().getTypeGoalId() == 3 && !goal.getAccomplished()) {
                return goal;
            }
        }
        return null;
    }

    public EvaluationDTO getEvaluationByUser(UserEntity user) {
        EvaluationEntity evaluationEntity = evaluationDAO.findByUserId(user);
        if (evaluationEntity != null) {
            return convertToDTO(evaluationEntity);
        }
        return null;
    }
}
