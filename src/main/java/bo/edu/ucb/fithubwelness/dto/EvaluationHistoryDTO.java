package bo.edu.ucb.fithubwelness.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class EvaluationHistoryDTO {

    private int evaluationId;
    private Double weight;
    private int height;
    private Date date;
    private Double imc;
    private String state;
    private UserDTO userId;
    private Timestamp validFrom;
    private Timestamp validTo;
    private Boolean isActive;

    public EvaluationHistoryDTO() {
    }

    public EvaluationHistoryDTO(int evaluationId, Double weight, int height, Date date, Double imc, String state,
            UserDTO userId, Timestamp validFrom, Timestamp validTo, Boolean isActive) {
        this.evaluationId = evaluationId;
        this.weight = weight;
        this.height = height;
        this.date = date;
        this.imc = imc;
        this.state = state;
        this.userId = userId;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.isActive = isActive;
    }

    // getters:

    public int getEvaluationId() {
        return evaluationId;
    }

    public Double getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public Date getDate() {
        return date;
    }

    public Double getImc() {
        return imc;
    }

    public String getState() {
        return state;
    }

    public UserDTO getUserId() {
        return userId;
    }

    public Timestamp getValidFrom() {
        return validFrom;
    }

    public Timestamp getValidTo() {
        return validTo;
    }

    public Boolean getActive() {
        return isActive;
    }

    // setters:

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setUserId(UserDTO userId) {
        this.userId = userId;
    }

    public void setValidFrom(Timestamp validFrom) {
        this.validFrom = validFrom;
    }

    public void setValidTo(Timestamp validTo) {
        this.validTo = validTo;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    // toString:

    @Override
    public String toString() {
        return "EvaluationHistoryDTO{" +
                "evaluationId=" + evaluationId +
                ", weight=" + weight +
                ", height=" + height +
                ", date=" + date +
                ", imc=" + imc +
                ", state='" + state + '\'' +
                ", userId=" + userId +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                ", isActive=" + isActive +
                '}';
    }
}
