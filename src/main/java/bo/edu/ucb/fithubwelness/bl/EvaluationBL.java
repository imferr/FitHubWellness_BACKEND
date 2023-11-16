package bo.edu.ucb.fithubwelness.bl;

import bo.edu.ucb.fithubwelness.dao.EvaluationDAO;
import bo.edu.ucb.fithubwelness.dto.EvaluationDTO;
import bo.edu.ucb.fithubwelness.dto.UserDTO;
import bo.edu.ucb.fithubwelness.entity.EvaluationEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationBL {

    private final EvaluationDAO evaluationDAO;
    private final UserBL userBL;

    @Autowired
    public EvaluationBL(EvaluationDAO evaluationDAO, UserBL userBL) {
        this.evaluationDAO = evaluationDAO;
        this.userBL = userBL;
    }

    public EvaluationDTO createEvaluation(Double weight, Integer height, UserEntity user) {
        Double imc = calculateIMC(weight, height);
        String state = determineState(imc);
        Date currentDate = new Date(System.currentTimeMillis());

        EvaluationEntity evaluation = new EvaluationEntity();
        evaluation.setWeight(weight);
        evaluation.setHeight(height);
        evaluation.setDate(currentDate);
        evaluation.setImc(imc);
        evaluation.setState(state);
        evaluation.setUserId(user);

        evaluation = evaluationDAO.save(evaluation);
        return convertToEvaluationDTO(evaluation);
    }

    private Double calculateIMC(Double weight, Integer height) {
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);
    }

    private String determineState(Double imc) {
        if (imc < 18.5) {
            return "Bajo peso";
        } else if (imc < 25) {
            return "Peso normal";
        } else if (imc < 30) {
            return "Sobrepeso";
        } else {
            return "Obesidad";
        }
    }

    private EvaluationDTO convertToEvaluationDTO(EvaluationEntity evaluation) {
        UserDTO userDTO = userBL.getUserById(evaluation.getUserId().getUserId());

        EvaluationDTO dto = new EvaluationDTO();
        dto.setEvaluationId(evaluation.getEvaluationId());
        dto.setWeight(evaluation.getWeight());
        dto.setHeight(evaluation.getHeight());
        dto.setDate(evaluation.getDate());
        dto.setImc(evaluation.getImc());
        dto.setState(evaluation.getState());
        dto.setUser(userDTO);

        return dto;
    }

    public List<EvaluationDTO> getAllEvaluations() {
        return evaluationDAO.findAll().stream()
                .map(this::convertToEvaluationDTO)
                .collect(Collectors.toList());
    }
}
