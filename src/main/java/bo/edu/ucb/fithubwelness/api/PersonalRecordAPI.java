package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.PersonalRecordBL;
import bo.edu.ucb.fithubwelness.dto.PersonalRecordDTO;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/personalrecord")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonalRecordAPI {

    private final PersonalRecordBL personalRecordBL;
    private static final Logger LOGGER = Logger.getLogger(PersonalRecordAPI.class.getName());

    @Autowired
    public PersonalRecordAPI(PersonalRecordBL personalRecordBL) {
        this.personalRecordBL = personalRecordBL;
    }

    @PostMapping("/create/user/{userId}")
    public ResponseEntity<PersonalRecordDTO> createPersonalRecord(@PathVariable int userId,
            @RequestBody PersonalRecordDTO personalRecordDTO) {
        LOGGER.info("Inicio de creacion de personal record");
        try {
            UserEntity user = new UserEntity();
            user.setUserId(userId);
            personalRecordDTO.setUserId(null);
            PersonalRecordDTO createdRecord = personalRecordBL.createPersonalRecord(personalRecordDTO, user);
            LOGGER.info("Personal record creado con exito");
            return ResponseEntity.ok(createdRecord);
        } catch (Exception e) {
            LOGGER.severe("Error al crear personal record: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Fin de creacion de personal record");
        }
    }

    @PutMapping("/user/{userId}/update/{recordId}")
    public ResponseEntity<PersonalRecordDTO> updatePersonalRecord(@PathVariable int userId,
            @PathVariable int recordId,
            @RequestBody PersonalRecordDTO personalRecordDTO) {
        LOGGER.info("Inicio de actualizacion de personal record");
        try {
            UserEntity user = new UserEntity();
            user.setUserId(userId);
            personalRecordDTO.setUserId(null);
            personalRecordDTO.setPersonalRecordId(recordId);
            PersonalRecordDTO updatedRecord = personalRecordBL.updatePersonalRecord(personalRecordDTO, user);
            LOGGER.info("Personal record actualizado con exito");
            return ResponseEntity.ok(updatedRecord);
        } catch (Exception e) {
            LOGGER.severe("Error al actualizar personal record: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Fin de actualizacion de personal record");
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PersonalRecordDTO>> getAllPersonalRecordsByUserId(@PathVariable int userId) {
        LOGGER.info("Inicio de obtencion de personal records");
        try {
            List<PersonalRecordDTO> records = personalRecordBL.findAllPersonalRecordsByUserId(userId);
            LOGGER.info("Personal records obtenidos con exito");
            return ResponseEntity.ok(records);
        } catch (Exception e) {
            LOGGER.severe("Error al obtener personal records: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Fin de obtencion de personal records");
        }
    }
}
