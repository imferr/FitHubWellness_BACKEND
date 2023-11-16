package bo.edu.ucb.fithubwelness.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.edu.ucb.fithubwelness.entity.TypeTrainingEntity;

public interface TypeTrainingDAO extends JpaRepository<TypeTrainingEntity, Integer> {
    
}
