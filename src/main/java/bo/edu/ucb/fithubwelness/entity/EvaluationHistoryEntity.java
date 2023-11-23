package bo.edu.ucb.fithubwelness.entity;

import java.sql.Date;
import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "evaluation_history")
public class EvaluationHistoryEntity {
    
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

    @Column(name = "estado", nullable = false)
    private String state;

    @ManyToOne
    @JoinColumn(name = "users_userid", referencedColumnName = "userid", nullable = false)
    private UserEntity userId;

    @Column(name = "valid_from", nullable = false)
    private Timestamp validFrom;

    @Column(name = "valid_to")
    private Timestamp validTo;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public EvaluationHistoryEntity() {
    }

    public EvaluationHistoryEntity(Integer evaluationId, Double weight, Integer height, Date date, Double imc, String state, UserEntity userId, Timestamp validFrom, Timestamp validTo, Boolean isActive) {
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

    public Timestamp getValidFrom() {
        return validFrom;
    }

    public Timestamp getValidTo() {
        return validTo;
    }

    public Boolean getActive() {
        return isActive;
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

    public void setValidFrom(Timestamp validFrom) {
        this.validFrom = validFrom;
    }

    public void setValidTo(Timestamp validTo) {
        this.validTo = validTo;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    //toString:

    @Override
    public String toString() {
        return "EvaluationHistoryEntity{" + 
                "evaluationId=" + evaluationId + 
                ", weight=" + weight + 
                ", height=" + height + 
                ", date='" + date + '\'' + 
                ", imc=" + imc + 
                ", state='" + state + '\'' + 
                ", userId=" + userId + 
                ", validFrom=" + validFrom + 
                ", validTo=" + validTo + 
                ", isActive=" + isActive + 
                '}';
    }
}
