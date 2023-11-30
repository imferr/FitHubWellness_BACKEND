package bo.edu.ucb.fithubwelness.dto;

import java.sql.Date;

public class DailyTrainingDTO {

    private int dailyTrainingId;
    private Date date;
    private TypeTrainingDTO typeTrainingId;
    private UserDTO userId;

    public DailyTrainingDTO() {
    }

    public DailyTrainingDTO(int dailyTrainingId, Date date, TypeTrainingDTO typeTrainingId, UserDTO userId) {
        this.dailyTrainingId = dailyTrainingId;
        this.date = date;
        this.typeTrainingId = typeTrainingId;
        this.userId = userId;
    }

    //getters:

    public int getDailyTrainingId() {
        return dailyTrainingId;
    }

    public Date getDate() {
        return date;
    }

    public TypeTrainingDTO getTypeTrainingId() {
        return typeTrainingId;
    }

    public UserDTO getUserId() {
        return userId;
    }

    //setters:

    public void setDailyTrainingId(int dailyTrainingId) {
        this.dailyTrainingId = dailyTrainingId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTypeTrainingId(TypeTrainingDTO typeTrainingId) {
        this.typeTrainingId = typeTrainingId;
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
                ", typeTrainingId=" + typeTrainingId +
                ", userId=" + userId + 
                '}';
    }
}