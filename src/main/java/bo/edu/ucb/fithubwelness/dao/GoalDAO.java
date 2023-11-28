package bo.edu.ucb.fithubwelness.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.edu.ucb.fithubwelness.entity.GoalEntity;

public interface GoalDAO extends JpaRepository<GoalEntity, Integer> {
    List<GoalEntity> findByUserId_UserId(int userId);    
}
