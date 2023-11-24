package bo.edu.ucb.fithubwelness.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.edu.ucb.fithubwelness.entity.EvaluationHistoryEntity;

public interface EvaluationHistoryDAO extends JpaRepository<EvaluationHistoryEntity, Integer> {
    
}
