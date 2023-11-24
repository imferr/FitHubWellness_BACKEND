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
    private Timestamp audDate;
    private String audHost;
    private Integer audUser;

    public EvaluationHistoryDTO() {
    }

    public EvaluationHistoryDTO(int evaluationId, Double weight, int height, Date date, Double imc, String state,
            UserDTO userId, Timestamp audDate, String audHost, Integer audUser) {
        this.evaluationId = evaluationId;
        this.weight = weight;
        this.height = height;
        this.date = date;
        this.imc = imc;
        this.state = state;
        this.userId = userId;
        this.audDate = audDate;
        this.audHost = audHost;
        this.audUser = audUser;
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

    public Timestamp getAudDate() {
        return audDate;
    }

    public String getAudHost() {
        return audHost;
    }

    public Integer getAudUser() {
        return audUser;
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

    public void setAudDate(Timestamp audDate) {
        this.audDate = audDate;
    }

    public void setAudHost(String audHost) {
        this.audHost = audHost;
    }

    public void setAudUser(Integer audUser) {
        this.audUser = audUser;
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
                ", audDate=" + audDate +
                ", audHost='" + audHost + '\'' +
                ", audUser='" + audUser + '\'' +
                '}';
    }
}
