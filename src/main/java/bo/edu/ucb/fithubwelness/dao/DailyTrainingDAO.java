package bo.edu.ucb.fithubwelness.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.edu.ucb.fithubwelness.entity.DailyTrainingEntity;

public interface DailyTrainingDAO extends JpaRepository<DailyTrainingEntity, Integer> {
    
}
