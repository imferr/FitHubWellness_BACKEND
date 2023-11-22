package bo.edu.ucb.fithubwelness.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.edu.ucb.fithubwelness.entity.DailyTrainingEntity;

public interface DailyTrainingDAO extends JpaRepository<DailyTrainingEntity, Integer> {
    List<DailyTrainingEntity> findAllByUserId(int userId);
}
