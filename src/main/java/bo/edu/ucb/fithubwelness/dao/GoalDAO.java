package bo.edu.ucb.fithubwelness.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.edu.ucb.fithubwelness.entity.GoalEntity;

public interface GoalDAO extends JpaRepository<GoalEntity, Integer> {
    
}
