package bo.edu.ucb.fithubwelness.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "evaluation")
public class EvaluationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluationid")
    private Integer evaluationId;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "imc", nullable = false)
    private Double imc;

    @Column(name = "state", nullable = false)
    private String state;

    @ManyToOne
    @JoinColumn(name = "users_userid", referencedColumnName = "userid", nullable = false)
    private UserEntity userId;

    public EvaluationEntity() {
    }

    public EvaluationEntity(Integer evaluationId, Double weight, Integer height, Date date, Double imc, String state, UserEntity userId) {
        this.evaluationId = evaluationId;
        this.weight = weight;
        this.height = height;
        this.date = date;
        this.imc = imc;
        this.state = state;
        this.userId = userId;
    }

    //getters:

    public int getEvaluationId() {
        return evaluationId;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getHeight() {
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

    public UserEntity getUserId() {
        return userId;
    }

    //setters:

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setHeight(Integer height) {
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

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    //toString:

    @Override
    public String toString() {
        return "EvaluationEntity{" + 
                "evaluationId=" + evaluationId + 
                ", weight=" + weight + 
                ", height=" + height + 
                ", date='" + date + '\'' + 
                ", imc=" + imc + 
                ", state='" + state + '\'' + 
                ", userId=" + userId + 
                '}';
    }
}
