/*package bo.edu.ucb.fithubwelness.bl;

import bo.edu.ucb.fithubwelness.dao.GoalDAO;
import bo.edu.ucb.fithubwelness.dto.ExerciseDTO;
import bo.edu.ucb.fithubwelness.dto.GoalDTO;
import bo.edu.ucb.fithubwelness.entity.GoalEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import bo.edu.ucb.fithubwelness.entity.TypeGoalEntity;
import bo.edu.ucb.fithubwelness.entity.ExerciseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GoalBL {

    private final GoalDAO goalDAO;
    private final UserBL userBL;
    private final ExerciseBL exerciseBL;
    private final TypeGoalBL typeGoalBL;

    @Autowired
    public GoalBL(GoalDAO goalDAO, UserBL userBL, ExerciseBL exerciseBL, TypeGoalBL typeGoalBL) {
        this.goalDAO = goalDAO;
        this.userBL = userBL;
        this.exerciseBL = exerciseBL;
        this.typeGoalBL = typeGoalBL;
    }

    public GoalDTO createGoal(GoalDTO goalDTO, String exerciseName) {
        GoalEntity goalEntity = new GoalEntity();
        goalEntity.setAccomplished(goalDTO.getAccomplished());
        goalEntity.setQuantity(goalDTO.getQuantity());

        UserEntity userEntity = userBL.findUserById(goalDTO.getUser().getUserId());
        goalEntity.setUserId(userEntity);

        TypeGoalEntity typeGoalEntity = typeGoalBL.findTypeGoalById(goalDTO.getTypeGoal().getTypeGoalId());
        goalEntity.setTypeGoalId(typeGoalEntity);

        if (exerciseName != null && !exerciseName.isEmpty()) {
            List<ExerciseDTO> exercises = exerciseBL.findExercisesByName(exerciseName);
            if (!exercises.isEmpty()) {
                ExerciseEntity exerciseEntity = new ExerciseEntity();
                goalEntity.setExerciseId(exerciseEntity);
            }
        }

        goalDAO.save(goalEntity);
        goalDTO.setGoalId(goalEntity.getGoalId());
        return goalDTO;
    }
}*/