package bo.edu.ucb.fithubwelness.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.edu.ucb.fithubwelness.entity.EvaluationEntity;

public interface EvaluationDAO extends JpaRepository<EvaluationEntity, Integer> {
    
}