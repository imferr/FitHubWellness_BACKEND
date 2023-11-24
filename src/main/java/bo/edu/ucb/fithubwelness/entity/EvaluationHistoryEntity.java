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

    @Column(name = "aud_date", nullable = false)
    private Timestamp audDate;

    @Column(name = "aud_host", nullable = false, length = 100)
    private String audHost;

    @Column(name = "aud_user")
    private Integer audUser;

    public EvaluationHistoryEntity() {
    }

    public EvaluationHistoryEntity(Integer evaluationId, Double weight, Integer height, Date date, Double imc,
            String state, UserEntity userId, Timestamp audDate, String audHost, Integer audUser) {
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

    public Integer getEvaluationId() {
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

    public void setEvaluationId(Integer evaluationId) {
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

    public void setAudDate(Timestamp audDate) {
        this.audDate = audDate;
    }

    public void setAudHost(String audHost) {
        this.audHost = audHost;
    }

    public void setAudUser(Integer audUser) {
        this.audUser = audUser;
    }

    @Override
    public String toString() {
        return "EvaluationHistoryEntity{" +
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