package bo.edu.ucb.fithubwelness.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bo.edu.ucb.fithubwelness.entity.GoalEntity;

public interface GoalDAO extends JpaRepository<GoalEntity, Integer> {
    List<GoalEntity> findByUserId_UserId(int userId);
    GoalEntity findByGoalId(Integer goalId);

    // query para encontrar objetivos similares, se hace una consulta a la base de datos para encontrar objetivos con los mismos parámetros
    @Query("SELECT g FROM GoalEntity g WHERE g.userId.userId = :userId AND g.typeGoalId.typeGoalId = :typeGoalId AND g.quantity = :quantity AND g.exerciseName = :exerciseName")
    List<GoalEntity> findSimilarGoals(@Param("userId") int userId, @Param("typeGoalId") int typeGoalId, @Param("quantity") Double double1, @Param("exerciseName") String exerciseName); 
    

    // query para encontrar objetivos similares para el typegoalid 3, se hace una consulta a la base de datos para encontrar objetivos con los mismos parámetros
    // no se toma en cuenta el nombre del ejercicio
    @Query("SELECT g FROM GoalEntity g WHERE g.userId.userId = :userId AND g.typeGoalId.typeGoalId = :typeGoalId AND g.quantity = :quantity")
    List<GoalEntity> findSimilarGoalsForTypeGoalId3(@Param("userId") int userId, @Param("typeGoalId") int typeGoalId, @Param("quantity") Double double1);
}
