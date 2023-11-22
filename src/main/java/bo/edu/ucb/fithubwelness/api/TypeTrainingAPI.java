package bo.edu.ucb.fithubwelness.api;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.edu.ucb.fithubwelness.bl.TypeTrainingBL;
import bo.edu.ucb.fithubwelness.entity.TypeTrainingEntity;

@RestController
@RequestMapping("/api/v1/TypeTraining")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TypeTrainingAPI {
    
    private final TypeTrainingBL TypeTrainingBL;
    private static final Logger LOGGER = Logger.getLogger(TypeTrainingAPI.class.getName());

    @Autowired
    public TypeTrainingAPI(TypeTrainingBL TypeTrainingBL) {
        this.TypeTrainingBL = TypeTrainingBL;
    }

    @GetMapping()
    public ResponseEntity<List<TypeTrainingEntity>> getAllTypeTraining() {
        LOGGER.info("Iniciando el proceso de obtener todos los tipos de entrenamientos");
        try {
            List<TypeTrainingEntity> TypeTrainings = TypeTrainingBL.getAllTypeTraining();
            return ResponseEntity.ok(TypeTrainings);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al obtener los tipos de entrenamientos");
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finalizando el proceso de obtener todos los tipos de entrenamientos");
        }
    }
}
