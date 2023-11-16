package bo.edu.ucb.fithubwelness.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.edu.ucb.fithubwelness.entity.AuthenticationEntity;

public interface AuthenticationDAO extends JpaRepository<AuthenticationEntity, Integer> {
    
}
