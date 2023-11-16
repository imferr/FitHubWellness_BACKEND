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
            UserDTO dto = convertToDTO(existingUser.get());
            dto.setNewUser(false);
            return dto;
        } else {
            UserEntity newUser = convertToEntity(userDTO);
            newUser = userDAO.save(newUser);
            UserDTO dto = convertToDTO(newUser);
            dto.setNewUser(true);
            return dto;
        }
    }

    private UserDTO convertToDTO(UserEntity userEntity) {
        return new UserDTO(
            userEntity.getUserId(), 
            userEntity.getName(), 
            userEntity.getEmail(), 
            userEntity.getBirthday(), 
            false
        );
    }    

    private UserEntity convertToEntity(UserDTO userDTO) {
        return new UserEntity(userDTO.getUserId(), userDTO.getName(), userDTO.getEmail(), userDTO.getBirthday());
    }

    public UserDTO getUserById(int id) {
        Optional<UserEntity> userEntity = userDAO.findById(id);
        return userEntity.map(this::convertToDTO).orElse(null);
    }
    
}
