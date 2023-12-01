package bo.edu.ucb.fithubwelness.dto;

import java.sql.Date;

public class GoalDTO {

    private int goalId;
    private Boolean accomplished;
    private Double quantity;
    private String exerciseName;
    private Date date;
    private Date accomplishedDate;
    private UserDTO userId;
    private TypeGoalDTO typeGoalId;

    public GoalDTO() {
    }

    public GoalDTO(int goalId, Boolean accomplished, Double quantity, String exerciseName, Date date, Date accomplishedDate, UserDTO userId, TypeGoalDTO typeGoalId) {
        this.goalId = goalId;
        this.accomplished = accomplished;
        this.quantity = quantity;
        this.exerciseName = exerciseName;
        this.date = date;
        this.accomplishedDate = accomplishedDate;
        this.userId = userId;
        this.typeGoalId = typeGoalId;
    }

    // getters:

    public int getGoalId() {
        return goalId;
    }

    public Boolean getAccomplished() {
        return accomplished;
    }

    public Double getQuantity() {
        return quantity;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public Date getDate() {
        return date;
    }

    public Date getAccomplishedDate() {
        return accomplishedDate;
    }

    public UserDTO getUserId() {
        return userId;
    }

    public TypeGoalDTO getTypeGoalId() {
        return typeGoalId;
    }

    // setters:

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public void setAccomplished(Boolean accomplished) {
        this.accomplished = accomplished;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAccomplishedDate(Date accomplishedDate) {
        this.accomplishedDate = accomplishedDate;
    }

    public void setUserId(UserDTO userId) {
        this.userId = userId;
    }

    public void setTypeGoalId(TypeGoalDTO typeGoalId) {
        this.typeGoalId = typeGoalId;
    }

    @Override
    public String toString() {
        return "GoalDTO{" + "goalId=" + goalId +
                ", accomplished=" + accomplished +
                ", quantity=" + quantity +
                ", exerciseName=" + exerciseName +
                ", date=" + date +
                ", accomplishedDate=" + accomplishedDate +
                ", userId=" + userId +
                ", typeGoalId=" + typeGoalId +
                '}';
    }

}