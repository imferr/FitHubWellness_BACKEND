package bo.edu.ucb.fithubwelness.dto;

public class GoalDTO {
    
    private int goalId;
    private Boolean accomplished;
    private Double quantity;
    private UserDTO user;
    private TypeGoalDTO typeGoal;
    private ExerciseDTO exercise;

    public GoalDTO() {
    }

    public GoalDTO(int goalId, Boolean accomplished, Double quantity, UserDTO user, TypeGoalDTO typeGoal, ExerciseDTO exercise) {
        this.goalId = goalId;
        this.accomplished = accomplished;
        this.quantity = quantity;
        this.user = user;
        this.typeGoal = typeGoal;
        this.exercise = exercise;
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

    public UserDTO getUser() {
        return user;
    }

    public TypeGoalDTO getTypeGoal() {
        return typeGoal;
    }

    public ExerciseDTO getExercise() {
        return exercise;
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

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public void setTypeGoal(TypeGoalDTO typeGoal) {
        this.typeGoal = typeGoal;
    }

    public void setExercise(ExerciseDTO exercise) {
        this.exercise = exercise;
    }

    //toString:

    @Override
    public String toString() {
        return "GoalDTO{" + 
                "goalId=" + goalId + 
                ", accomplished=" + accomplished + 
                ", quantity=" + quantity + 
                ", user=" + user + 
                ", typeGoal=" + typeGoal + 
                ", exercise=" + exercise + 
                '}';
    }
}
