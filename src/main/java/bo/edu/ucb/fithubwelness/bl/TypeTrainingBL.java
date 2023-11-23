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
        TypeTrainingDAO.save(new TypeTrainingEntity(1, "Tren Superior"));
        TypeTrainingDAO.save(new TypeTrainingEntity(2, "Tren Inferior"));
    }

    public List<TypeTrainingEntity> getAllTypeTraining() {
        return TypeTrainingDAO.findAll();
    }
}
