package bo.edu.ucb.fithubwelness.dto;

public class ChatGptResponseDTO {
    
    private int chatId;
    private String response;
    private EvaluationDTO evaluation;
    private DailyTrainingDTO dailyTraining;

    public ChatGptResponseDTO() {
    }

    public ChatGptResponseDTO(int chatId, String response, EvaluationDTO evaluation, DailyTrainingDTO dailyTraining) {
        this.chatId = chatId;
        this.response = response;
        this.evaluation = evaluation;
        this.dailyTraining = dailyTraining;
    }

    //getters:

    public int getChatId() {
        return chatId;
    }

    public String getResponse() {
        return response;
    }

    public EvaluationDTO getEvaluation() {
        return evaluation;
    }

    public DailyTrainingDTO getDailyTraining() {
        return dailyTraining;
    }

    //setters:

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setEvaluation(EvaluationDTO evaluation) {
        this.evaluation = evaluation;
    }

    public void setDailyTraining(DailyTrainingDTO dailyTraining) {
        this.dailyTraining = dailyTraining;
    }

    //toString:

    @Override
    public String toString() {
        return "ChatGptResponseDTO{" + 
                "chatId=" + chatId + 
                ", response=" + response + 
                ", evaluation=" + evaluation + 
                ", dailyTraining=" + dailyTraining + 
                '}';
    }
}
