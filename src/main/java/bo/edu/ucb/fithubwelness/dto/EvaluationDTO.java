package bo.edu.ucb.fithubwelness.dto;

import java.sql.Date;

public class EvaluationDTO {
    
    private int evaluationId;
    private Double weight;
    private int height;
    private Date date;
    private Double imc;
    private String state;
    private UserDTO user;

    public EvaluationDTO() {
    }

    public EvaluationDTO(int evaluationId, Double weight, int height, Date date, Double imc, String state, UserDTO user) {
        this.evaluationId = evaluationId;
        this.weight = weight;
        this.height = height;
        this.date = date;
        this.imc = imc;
        this.state = state;
        this.user = user;
    }

    //getters:

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
        return user;
    }

    //setters:

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

    public void setUserId(UserDTO user) {
        this.user = user;
    }

    //toString:

    @Override
    public String toString() {
        return "EvaluationDTO{" +
                "evaluationId=" + evaluationId +
                ", weight=" + weight +
                ", height=" + height +
                ", date=" + date +
                ", imc=" + imc +
                ", state='" + state + '\'' +
                ", user=" + user +
                '}';
    }
}