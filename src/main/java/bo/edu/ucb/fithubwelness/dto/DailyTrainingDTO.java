package bo.edu.ucb.fithubwelness.dto;

import java.sql.Timestamp;

public class DailyTrainingDTO {

    private int dailyTrainingId;
    private Timestamp date;
    private TypeTrainingDTO typeTraining;
    private UserDTO userId;

    public DailyTrainingDTO() {
    }

    public DailyTrainingDTO(int dailyTrainingId, Timestamp date, TypeTrainingDTO typeTraining, UserDTO userId) {
        this.dailyTrainingId = dailyTrainingId;
        this.date = date;
        this.typeTraining = typeTraining;
        this.userId = userId;
    }

    //getters:

    public int getDailyTrainingId() {
        return dailyTrainingId;
    }

    public Timestamp getDate() {
        return date;
    }

    public TypeTrainingDTO getTypeTraining() {
        return typeTraining;
    }

    public UserDTO getUserId() {
        return userId;
    }

    //setters:

    public void setDailyTrainingId(int dailyTrainingId) {
        this.dailyTrainingId = dailyTrainingId;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setTypeTraining(TypeTrainingDTO typeTraining) {
        this.typeTraining = typeTraining;
    }

    public void setUserId(UserDTO userId) {
        this.userId = userId;
    }

    //toString:

    @Override
    public String toString() {
        return "DailyTrainingDTO{" + 
                "dailyTrainingId=" + dailyTrainingId + 
                ", date=" + date + 
                ", typeTraining=" + typeTraining + 
                ", user=" + userId + 
                '}';
    }
}