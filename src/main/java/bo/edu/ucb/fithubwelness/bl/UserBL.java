package bo.edu.ucb.fithubwelness.bl;

import bo.edu.ucb.fithubwelness.dao.UserDAO;
import bo.edu.ucb.fithubwelness.dto.UserDTO;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBL {

    private final UserDAO userDAO;

    @Autowired
    public UserBL(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDTO findOrCreateUser(UserDTO userDTO) {
        Optional<UserEntity> existingUser = userDAO.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            UserEntity userEntity = existingUser.get();
            UserDTO dto = new UserDTO(
                userEntity.getUserId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getBirthday()
            );
            return dto;
        } else {
            UserEntity newUser = new UserEntity();
            newUser.setName(userDTO.getName());
            newUser.setEmail(userDTO.getEmail());
            newUser.setBirthday(userDTO.getBirthday());
    
            newUser = userDAO.save(newUser);
    
            UserDTO dto = new UserDTO(
                newUser.getUserId(),
                newUser.getName(),
                newUser.getEmail(),
                newUser.getBirthday()
            );
            return dto;
        }
    }

    public boolean emailExists(String email) {
    Optional<UserEntity> existingUser = userDAO.findByEmail(email);
    return existingUser.isPresent();
}

}
