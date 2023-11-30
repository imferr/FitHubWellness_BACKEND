package bo.edu.ucb.fithubwelness.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chatgpt_response")
public class ChatGptResponseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatid")
    private Integer chatId;

    @Column(name = "response", nullable = false, length = 5000)
    private String response;

    @ManyToOne
    @JoinColumn(name = "evaluation_evaluationid", referencedColumnName = "evaluationid", nullable = false)
    private EvaluationEntity evaluationId;

    @ManyToOne
    @JoinColumn(name = "daily_training_dailyid", referencedColumnName = "dailyid", nullable = false)
    private DailyTrainingEntity dailyTrainingId;

    @ManyToOne
    @JoinColumn(name = "users_userid", referencedColumnName = "userid", nullable = false)
    private UserEntity userId;

    public ChatGptResponseEntity() {
    }

    public ChatGptResponseEntity(Integer chatId, String response, EvaluationEntity evaluationId, DailyTrainingEntity dailyTrainingId, UserEntity userId) {
        this.chatId = chatId;
        this.response = response;
        this.evaluationId = evaluationId;
        this.dailyTrainingId = dailyTrainingId;
        this.userId = userId;
    }

    //getters:

    public int getChatId() {
        return chatId;
    }

    public String getResponse() {
        return response;
    }

    public EvaluationEntity getEvaluationId() {
        return evaluationId;
    }

    public DailyTrainingEntity getDailyTrainingId() {
        return dailyTrainingId;
    }

    public UserEntity getUserId() {
        return userId;
    }

    //setters:

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setEvaluationId(EvaluationEntity evaluationId) {
        this.evaluationId = evaluationId;
    }

    public void setDailyTrainingId(DailyTrainingEntity dailyTrainingId) {
        this.dailyTrainingId = dailyTrainingId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    //toString:

    @Override
    public String toString() {
        return "ChatGptResponseEntity{" + 
                "chatId=" + chatId + 
                ", response='" + response + '\'' + 
                ", evaluationId=" + evaluationId + 
                ", dailyTrainingId=" + dailyTrainingId + 
                ", userId=" + userId +
                '}';
    }
}
