package bo.edu.ucb.fithubwelness.bl;

import bo.edu.ucb.fithubwelness.dao.GoalDAO;
import bo.edu.ucb.fithubwelness.dao.TypeGoalDAO;
import bo.edu.ucb.fithubwelness.dto.ExerciseDTO;
import bo.edu.ucb.fithubwelness.dto.GoalDTO;
import bo.edu.ucb.fithubwelness.dto.TypeGoalDTO;
import bo.edu.ucb.fithubwelness.dto.UserDTO;
import bo.edu.ucb.fithubwelness.entity.GoalEntity;
import bo.edu.ucb.fithubwelness.entity.TypeGoalEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GoalBL {

    private final GoalDAO goalDAO;
    private final ExerciseBL exerciseBL;
    private final UserBL userBL;
    private final TypeGoalDAO typeGoalDAO;

    @Autowired
    public GoalBL(GoalDAO goalDAO, ExerciseBL exerciseBL, UserBL userBL, TypeGoalDAO typeGoalDAO) {
        this.goalDAO = goalDAO;
        this.exerciseBL = exerciseBL;
        this.userBL = userBL;
        this.typeGoalDAO = typeGoalDAO;
    }

    public GoalDTO createGoal(GoalDTO goalDTO) {
        goalDTO.setAccomplished(false);

        if (goalDTO.getTypeGoalId().getTypeGoalId() == 1 || goalDTO.getTypeGoalId().getTypeGoalId() == 2) {
            List<ExerciseDTO> exercises = exerciseBL.findExercisesByName(goalDTO.getExerciseName());
            if (!exercises.isEmpty()) {
                goalDTO.setExerciseName(exercises.get(0).getName());
            }
        } else {
            goalDTO.setExerciseName("-");
        }

        int userId = goalDTO.getUserId().getUserId();
        UserEntity userEntity = userBL.findUserById(userId);

        int typeGoalId = goalDTO.getTypeGoalId().getTypeGoalId();
        TypeGoalEntity typeGoalEntity = findTypeGoalById(typeGoalId);

        GoalEntity goalEntity = new GoalEntity();
        goalEntity.setAccomplished(goalDTO.getAccomplished());
        goalEntity.setQuantity(goalDTO.getQuantity());
        goalEntity.setExerciseName(goalDTO.getExerciseName());
        goalEntity.setUserId(userEntity);
        goalEntity.setTypeGoalId(typeGoalEntity);

        goalDAO.save(goalEntity);
        return convertToDTO(goalEntity);
    }

    private TypeGoalEntity findTypeGoalById(int typeGoalId) {
        return typeGoalDAO.findById(typeGoalId)
                .orElseThrow(() -> new RuntimeException("TypeGoal not found"));
    }

    private GoalDTO convertToDTO(GoalEntity entity) {
        GoalDTO dto = new GoalDTO();
        dto.setGoalId(entity.getGoalId());
        dto.setAccomplished(entity.getAccomplished());
        dto.setQuantity(entity.getQuantity());
        dto.setExerciseName(entity.getExerciseName());

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(entity.getUserId().getUserId());
        dto.setUserId(userDTO);

        TypeGoalDTO typeGoalDTO = new TypeGoalDTO();
        typeGoalDTO.setTypeGoalId(entity.getTypeGoalId().getTypeGoalId());
        typeGoalDTO.setTypeGoal(entity.getTypeGoalId().getTypeGoal());
        dto.setTypeGoalId(typeGoalDTO);

        return dto;
    }

    public GoalDTO getGoalById(int goalId) {
        Optional<GoalEntity> goalEntity = goalDAO.findById(goalId);
        if (goalEntity.isPresent()) {
            return convertToDTO(goalEntity.get());
        } else {
            throw new RuntimeException("Goal not found");
        }
    }

}
