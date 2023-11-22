package bo.edu.ucb.fithubwelness.bl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.edu.ucb.fithubwelness.dao.DailyTrainingDAO;
import bo.edu.ucb.fithubwelness.dao.TypeTrainingDAO;
import bo.edu.ucb.fithubwelness.dao.UserDAO;
import bo.edu.ucb.fithubwelness.dto.DailyTrainingDTO;
import bo.edu.ucb.fithubwelness.dto.TypeTrainingDTO;
import bo.edu.ucb.fithubwelness.dto.UserDTO;
import bo.edu.ucb.fithubwelness.entity.DailyTrainingEntity;
import bo.edu.ucb.fithubwelness.entity.TypeTrainingEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;

@Service
public class DailyTrainingBL {

    private final DailyTrainingDAO dailyTrainingDAO;
    private final TypeTrainingDAO typeTrainingDAO;
    private final UserDAO userDAO;

    @Autowired
    public DailyTrainingBL(DailyTrainingDAO dailyTrainingDAO, TypeTrainingDAO typeTrainingDAO, UserDAO userDAO) {
        this.dailyTrainingDAO = dailyTrainingDAO;
        this.typeTrainingDAO = typeTrainingDAO;
        this.userDAO = userDAO;
    }

    public DailyTrainingDTO createDailyTraining(int userId, int typeTrainingId) {
        DailyTrainingEntity entity = new DailyTrainingEntity();
        entity.setDate(new java.sql.Timestamp(System.currentTimeMillis()));

        Optional<TypeTrainingEntity> typeTrainingEntity = typeTrainingDAO.findById(typeTrainingId);
        entity.setTypeTrainingId(typeTrainingEntity.get());

        Optional<UserEntity> userEntity = userDAO.findById(userId);
        entity.setUserId(userEntity.get());

        DailyTrainingEntity result = dailyTrainingDAO.save(entity);

        DailyTrainingDTO resultDTO = new DailyTrainingDTO();
        resultDTO.setDailyTrainingId(result.getDailyTrainingId());
        resultDTO.setDate(result.getDate());

        TypeTrainingDTO typeTrainingDTO = new TypeTrainingDTO();
        typeTrainingDTO.setTypeTrainingId(typeTrainingEntity.get().getTypeTrainingId());
        typeTrainingDTO.setTypeTraining(typeTrainingEntity.get().getTypeTraining());
        resultDTO.setTypeTraining(typeTrainingDTO);

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.get().getUserId());
        resultDTO.setUserId(userDTO);

        return resultDTO;
    }

    public List<Map<String, Object>> findAllDailyTrainingByUserId(int userId) {
        List<DailyTrainingEntity> trainingEntities = dailyTrainingDAO.findAllByUserId(userId);
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (DailyTrainingEntity entity : trainingEntities) {
            Map<String, Object> map = new HashMap<>();
            map.put("dailyTrainingId", entity.getDailyTrainingId());
            map.put("date", entity.getDate());

            Map<String, Object> typeTrainingMap = new HashMap<>();
            typeTrainingMap.put("typeTrainingId", entity.getTypeTrainingId().getTypeTrainingId());
            typeTrainingMap.put("typeTraining", entity.getTypeTrainingId().getTypeTraining());
            map.put("typeTraining", typeTrainingMap);

            Map<String, Object> userMap = new HashMap<>();
            userMap.put("userId", entity.getUserId().getUserId());
            map.put("user", userMap);

            resultList.add(map);
        }
        return resultList;
    }
}
