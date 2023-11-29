package bo.edu.ucb.fithubwelness.dto;

public class LastEvaluationDTO {

    private int lastEvaluationId;
    private Double weight;
    private UserDTO userId;

    public LastEvaluationDTO() {
    }

    public LastEvaluationDTO(int lastEvaluationId, Double weight, UserDTO userId) {
        this.lastEvaluationId = lastEvaluationId;
        this.weight = weight;
        this.userId = userId;
    }

    // getters:

    public int getLastEvaluationId() {
        return lastEvaluationId;
    }

    public Double getWeight() {
        return weight;
    }

    public UserDTO getUserId() {
        return userId;
    }

    // setters:

    public void setLastEvaluationId(int lastEvaluationId) {
        this.lastEvaluationId = lastEvaluationId;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setUserId(UserDTO userId) {
        this.userId = userId;
    }

    // toString:
    @Override
    public String toString() {
        return "LastEvaluationDTO{" + "lastEvaluationId=" + lastEvaluationId +
                ", weight=" + weight + ", userId=" + userId + '}';
    }
}
