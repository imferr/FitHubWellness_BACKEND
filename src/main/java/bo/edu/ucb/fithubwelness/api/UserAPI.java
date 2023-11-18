package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.UserBL;
import bo.edu.ucb.fithubwelness.dto.UserDTO;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserAPI {

    private final UserBL userBL;
    private static final Logger LOGGER = Logger.getLogger(UserAPI.class.getName());

    @Autowired
    public UserAPI(UserBL userBL) {
        this.userBL = userBL;
    }

    @PostMapping("/findOrCreate")
    public ResponseEntity<UserDTO> findOrCreateUser(@RequestBody UserDTO userDTO) {
        LOGGER.info("Iniciando el proceso de creación de usuario");
        try {
            boolean emailExists = userBL.emailExists(userDTO.getEmail());
            if (!emailExists) {
                LOGGER.info("Correo no encontrado en la base de datos. Redirigiendo a la página de first evaluation.");
                return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/firstevaluation").build();
            }
            UserDTO user = userBL.findOrCreateUser(userDTO);
            LOGGER.info("Usuario creado exitosamente");
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al crear el usuario");
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finalizando el proceso de creación de usuario");
        }
    }

}
