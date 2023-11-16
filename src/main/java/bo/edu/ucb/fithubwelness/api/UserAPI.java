package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.UserBL;
import bo.edu.ucb.fithubwelness.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserAPI {

    private final UserBL userBL;

    @Autowired
    public UserAPI(UserBL userBL) {
        this.userBL = userBL;
    }

    @PostMapping("/findOrCreate")
    public ResponseEntity<UserDTO> findOrCreateUser(@RequestBody UserDTO userDTO) {
        UserDTO user = userBL.findOrCreateUser(userDTO);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        UserDTO user = userBL.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
