package bo.edu.ucb.fithubwelness.dto;

import java.sql.Timestamp;

public class DailyTrainingDTO {

    private int dailyTrainingId;
    private Timestamp date;
    private TypeTrainingDTO typeTraining;
    private UserDTO user;

    public DailyTrainingDTO() {
    }

    public DailyTrainingDTO(int dailyTrainingId, Timestamp date, TypeTrainingDTO typeTraining, UserDTO user) {
        this.dailyTrainingId = dailyTrainingId;
        this.date = date;
        this.typeTraining = typeTraining;
        this.user = user;
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

    public UserDTO getUser() {
        return user;
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

    public void setUser(UserDTO user) {
        this.user = user;
    }

    //toString:

    @Override
    public String toString() {
        return "DailyTrainingDTO{" + 
                "dailyTrainingId=" + dailyTrainingId + 
                ", date=" + date + 
                ", typeTraining=" + typeTraining + 
                ", user=" + user + 
                '}';
    }
}