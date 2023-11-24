package bo.edu.ucb.fithubwelness.bl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.edu.ucb.fithubwelness.dao.TypeGoalDAO;
import bo.edu.ucb.fithubwelness.entity.TypeGoalEntity;
import jakarta.annotation.PostConstruct;

@Service
public class TypeGoalBL {

    private TypeGoalDAO typeGoalDAO;

    @Autowired
    public TypeGoalBL(TypeGoalDAO typeGoalDAO) {
        this.typeGoalDAO = typeGoalDAO;
    }

    @PostConstruct
    public void init() {
        if (typeGoalDAO.count() == 0) {
            insertInitialData();
        }
    }

    private void insertInitialData() {
        typeGoalDAO.save(new TypeGoalEntity(1, "Lograr levantar un peso determinado"));
        typeGoalDAO.save(new TypeGoalEntity(2, "Conseguir un número específico de repeticiones"));
        typeGoalDAO.save(new TypeGoalEntity(3, "Bajar de peso"));
    }

    public List<TypeGoalEntity> getAllTypeGoal() {
        return typeGoalDAO.findAll();
    }
}
