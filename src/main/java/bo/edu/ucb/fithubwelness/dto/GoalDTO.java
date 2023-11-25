package bo.edu.ucb.fithubwelness.dto;

public class GoalDTO {
    
    private int goalId;
    private Boolean accomplished;
    private Double quantity;
    private String exerciseName;
    private UserDTO user;
    private TypeGoalDTO typeGoal;

    public GoalDTO() {
    }

    public GoalDTO(int goalId, Boolean accomplished, Double quantity, String exerciseName, UserDTO user, TypeGoalDTO typeGoal) {
        this.goalId = goalId;
        this.accomplished = accomplished;
        this.quantity = quantity;
        this.exerciseName = exerciseName;
        this.user = user;
        this.typeGoal = typeGoal;
    }

    //getters:

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

    public UserDTO getUser() {
        return user;
    }

    public TypeGoalDTO getTypeGoal() {
        return typeGoal;
    }

    //setters:

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

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public void setTypeGoal(TypeGoalDTO typeGoal) {
        this.typeGoal = typeGoal;
    }

    @Override
    public String toString() {
        return "GoalDTO{" +
                "goalId=" + goalId +
                ", accomplished=" + accomplished +
                ", quantity=" + quantity +
                ", exerciseName='" + exerciseName + '\'' +
                ", user=" + user +
                ", typeGoal=" + typeGoal +
                '}';
    }
}