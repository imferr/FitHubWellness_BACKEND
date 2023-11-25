package bo.edu.ucb.fithubwelness.bl;

import bo.edu.ucb.fithubwelness.dao.GoalDAO;
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

    @Autowired
    public GoalBL(GoalDAO goalDAO, ExerciseBL exerciseBL, UserBL userBL) {
        this.goalDAO = goalDAO;
        this.exerciseBL = exerciseBL;
        this.userBL = userBL;
    }

    public GoalDTO createGoal(GoalDTO goalDTO) {
        goalDTO.setAccomplished(false);

        if (goalDTO.getTypeGoal().getTypeGoalId() == 1 || goalDTO.getTypeGoal().getTypeGoalId() == 2) {
            List<ExerciseDTO> exercises = exerciseBL.findExercisesByName(goalDTO.getExerciseName());
            if (!exercises.isEmpty()) {
                goalDTO.setExerciseName(exercises.get(0).getName());
            }
        } else {
            goalDTO.setExerciseName(null);
        }

        UserEntity user = userBL.findUserById(goalDTO.getUser().getUserId());
        GoalEntity goalEntity = convertToEntity(goalDTO, user);

        goalDAO.save(goalEntity);
        return convertToDTO(goalEntity);
    }

    private GoalEntity convertToEntity(GoalDTO dto, UserEntity userEntity) {
        GoalEntity entity = new GoalEntity();
        entity.setGoalId(dto.getGoalId());
        entity.setAccomplished(dto.getAccomplished());
        entity.setQuantity(dto.getQuantity());
        entity.setExerciseName(dto.getExerciseName());
        entity.setUserId(userEntity);
        entity.setTypeGoalId(new TypeGoalEntity(dto.getTypeGoal().getTypeGoalId(), dto.getTypeGoal().getTypeGoal()));
        return entity;
    }

    private GoalDTO convertToDTO(GoalEntity entity) {
        GoalDTO dto = new GoalDTO();
        dto.setGoalId(entity.getGoalId());
        dto.setAccomplished(entity.getAccomplished());
        dto.setQuantity(entity.getQuantity());
        dto.setExerciseName(entity.getExerciseName());

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(entity.getUserId().getUserId());
        userDTO.setName(entity.getUserId().getName());
        userDTO.setEmail(entity.getUserId().getEmail());
        userDTO.setBirthday(entity.getUserId().getBirthday());
        dto.setUser(userDTO);

        TypeGoalDTO typeGoalDTO = new TypeGoalDTO();
        typeGoalDTO.setTypeGoalId(entity.getTypeGoalId().getTypeGoalId());
        typeGoalDTO.setTypeGoal(entity.getTypeGoalId().getTypeGoal());
        dto.setTypeGoal(typeGoalDTO);

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
