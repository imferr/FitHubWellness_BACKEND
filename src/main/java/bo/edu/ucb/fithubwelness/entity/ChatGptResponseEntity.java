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

    public ChatGptResponseEntity() {
    }

    public ChatGptResponseEntity(Integer chatId, String response, EvaluationEntity evaluationId, DailyTrainingEntity dailyTrainingId) {
        this.chatId = chatId;
        this.response = response;
        this.evaluationId = evaluationId;
        this.dailyTrainingId = dailyTrainingId;
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

    //toString:

    @Override
    public String toString() {
        return "ChatGptResponseEntity{" + 
                "chatId=" + chatId + 
                ", response='" + response + '\'' + 
                ", evaluationId=" + evaluationId + 
                ", dailyTrainingId=" + dailyTrainingId + 
                '}';
    }
}
