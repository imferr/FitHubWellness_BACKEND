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

import java.util.ArrayList;
import java.util.List;

@Service
public class GoalBL {

    private final GoalDAO goalDAO;
    private final ExerciseBL exerciseBL;

    @Autowired
    public GoalBL(GoalDAO goalDAO, ExerciseBL exerciseBL) {
        this.goalDAO = goalDAO;
        this.exerciseBL = exerciseBL;
    }

    public GoalDTO createGoal(GoalDTO goalDTO, UserEntity user, TypeGoalEntity typeGoal) {
        goalDTO.setAccomplished(false);

        if (goalDTO.getTypeGoalId().getTypeGoalId() == 1 || goalDTO.getTypeGoalId().getTypeGoalId() == 2) {
            List<ExerciseDTO> exercises = exerciseBL.findExercisesByName(goalDTO.getExerciseName());
            if (!exercises.isEmpty()) {
                goalDTO.setExerciseName(exercises.get(0).getName());
            }
        } else {
            goalDTO.setExerciseName("-");
        }

        GoalEntity entity =  new GoalEntity();
        entity.setAccomplished(goalDTO.getAccomplished());
        entity.setQuantity(goalDTO.getQuantity());
        entity.setExerciseName(goalDTO.getExerciseName());
        entity.setUserId(user);
        entity.setTypeGoalId(typeGoal);
        entity = goalDAO.save(entity);
        return convertToDTO(entity);
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

    public List<GoalDTO> findGoalsByUserId(int userId) {
        List<GoalEntity> goalEntities = goalDAO.findByUserId_UserId(userId);

        List<GoalDTO> goalDTOs = new ArrayList<>();
        for (GoalEntity goalEntity : goalEntities) {
            goalDTOs.add(convertToDTO(goalEntity));
        }
        return goalDTOs;
    }

}
