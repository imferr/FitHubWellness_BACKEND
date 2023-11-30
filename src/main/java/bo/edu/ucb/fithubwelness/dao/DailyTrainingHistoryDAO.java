package bo.edu.ucb.fithubwelness.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.edu.ucb.fithubwelness.entity.DailyTrainingHistoryEntity;

public interface DailyTrainingHistoryDAO extends JpaRepository<DailyTrainingHistoryEntity, Integer> {
    
}
