package bo.edu.ucb.fithubwelness.bl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.edu.ucb.fithubwelness.dao.TypeTrainingDAO;
import bo.edu.ucb.fithubwelness.entity.TypeTrainingEntity;
import jakarta.annotation.PostConstruct;

@Service
public class TypeTrainingBL {

    private TypeTrainingDAO TypeTrainingDAO;

    @Autowired
    public TypeTrainingBL(TypeTrainingDAO TypeTrainingDAO) {
        this.TypeTrainingDAO = TypeTrainingDAO;
    }

    @PostConstruct
    public void init() {
        if (TypeTrainingDAO.count() == 0) {
            insertInitialData();
        }
    }

    private void insertInitialData() {
        TypeTrainingDAO.save(new TypeTrainingEntity(1, "Biceps"));
        TypeTrainingDAO.save(new TypeTrainingEntity(2, "Triceps"));
        TypeTrainingDAO.save(new TypeTrainingEntity(3, "Hombros"));
        TypeTrainingDAO.save(new TypeTrainingEntity(4, "Pecho"));
        TypeTrainingDAO.save(new TypeTrainingEntity(5, "Espalda"));
        TypeTrainingDAO.save(new TypeTrainingEntity(6, "Cuadriceps"));
        TypeTrainingDAO.save(new TypeTrainingEntity(7, "Femorales"));
        TypeTrainingDAO.save(new TypeTrainingEntity(8, "Glúteos"));
        TypeTrainingDAO.save(new TypeTrainingEntity(9, "Gemelos"));
        TypeTrainingDAO.save(new TypeTrainingEntity(10, "Abdomen"));
    }

    public List<TypeTrainingEntity> getAllTypeTraining() {
        return TypeTrainingDAO.findAll();
    }
}
