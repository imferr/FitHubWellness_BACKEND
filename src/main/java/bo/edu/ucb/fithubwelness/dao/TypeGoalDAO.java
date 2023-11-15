package bo.edu.ucb.fithubwelness.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.edu.ucb.fithubwelness.entity.TypeGoalEntity;

public interface TypeGoalDAO extends JpaRepository<TypeGoalEntity, Integer> {
    
}
