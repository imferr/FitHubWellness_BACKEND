package bo.edu.ucb.fithubwelness.dto;

public class GoalDTO {

    private int goalId;
    private Boolean accomplished;
    private Double quantity;
    private String exerciseName;
    private UserDTO userId;
    private TypeGoalDTO typeGoalId;

    public GoalDTO() {
    }

    public GoalDTO(int goalId, Boolean accomplished, Double quantity, String exerciseName, UserDTO userId,
            TypeGoalDTO typeGoalId) {
        this.goalId = goalId;
        this.accomplished = accomplished;
        this.quantity = quantity;
        this.exerciseName = exerciseName;
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
                ", userId=" + userId +
                ", typeGoalId=" + typeGoalId +
                '}';
    }

}