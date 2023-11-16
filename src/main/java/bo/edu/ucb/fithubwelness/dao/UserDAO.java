package bo.edu.ucb.fithubwelness.dao;

import bo.edu.ucb.fithubwelness.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
