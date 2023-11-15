package bo.edu.ucb.fithubwelness.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.edu.ucb.fithubwelness.entity.ExerciseEntity;

public interface ExerciseDAO extends JpaRepository<ExerciseEntity, Integer> {
    
}
