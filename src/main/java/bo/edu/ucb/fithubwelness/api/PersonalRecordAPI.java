package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.PersonalRecordBL;
import bo.edu.ucb.fithubwelness.dto.PersonalRecordDTO;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> createPersonalRecord(@PathVariable int userId,
            @RequestBody PersonalRecordDTO personalRecordDTO) {
        LOGGER.info("Inicio de creacion de personal record");
        try {
            UserEntity user = new UserEntity();
            user.setUserId(userId);
            personalRecordDTO.setUserId(null);
            PersonalRecordDTO createdRecord = personalRecordBL.createPersonalRecord(personalRecordDTO, user);
            LOGGER.info("Personal record creado con exito");
            return ResponseEntity.ok(createdRecord);
        } catch (RuntimeException e) {
            LOGGER.severe("Error al crear personal record: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.severe("Error interno al crear personal record: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("Fin de creacion de personal record");
        }
    }

    @PutMapping("/user/{userId}/update/{recordId}")
    public ResponseEntity<?> updatePersonalRecord(@PathVariable int userId, @PathVariable int recordId,
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
        } catch (RuntimeException e) {
            LOGGER.severe("Error al actualizar personal record: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.severe("Error interno al actualizar personal record: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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

    @GetMapping("/{recordId}")
    public ResponseEntity<PersonalRecordDTO> getPersonalRecordById(@PathVariable int recordId) {
        LOGGER.info("Obteniendo Personal Record con ID: " + recordId);
        try {
            PersonalRecordDTO record = personalRecordBL.findPersonalRecordById(recordId);
            LOGGER.info("Personal Record obtenido con éxito");
            return ResponseEntity.ok(record);
        } catch (Exception e) {
            LOGGER.severe("Error al obtener Personal Record: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } finally {
            LOGGER.info("Fin de obtención de Personal Record");
        }
    }

    @GetMapping("/latest/user/{userId}")
    public ResponseEntity<PersonalRecordDTO> getLatestPersonalRecordByUserId(@PathVariable int userId) {
        LOGGER.info("Inicio de obtención del último personal record para el usuario con ID: " + userId);
        try {
            PersonalRecordDTO latestRecord = personalRecordBL.findLatestPersonalRecordByUserId(userId);
            LOGGER.info("Último personal record obtenido con éxito");
            return ResponseEntity.ok(latestRecord);
        } catch (RuntimeException e) {
            LOGGER.severe("Error al obtener el último personal record: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            LOGGER.severe("Error interno al obtener el último personal record: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("Fin de obtención del último personal record");
        }
    }

}
