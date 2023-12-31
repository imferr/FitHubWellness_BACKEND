package bo.edu.ucb.fithubwelness.dto;

public class ChatGptResponseDTO {
    
    private int chatId;
    private String question;
    private String response;
    private EvaluationDTO evaluation;
    private DailyTrainingDTO dailyTraining;
    private UserDTO userId;

    public ChatGptResponseDTO() {
    }

    public ChatGptResponseDTO(int chatId, String question, String response, EvaluationDTO evaluation, DailyTrainingDTO dailyTraining, UserDTO userId) {
        this.chatId = chatId;
        this.question = question;
        this.response = response;
        this.evaluation = evaluation;
        this.dailyTraining = dailyTraining;
        this.userId = userId;
    }

    //getters:

    public int getChatId() {
        return chatId;
    }

    public String getQuestion() {
        return question;
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

    public UserDTO getUserId() {
        return userId;
    }

    //setters:

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public void setUserId(UserDTO userId) {
        this.userId = userId;
    }

    //toString:

    @Override
    public String toString() {
        return "ChatGptResponseDTO{" + 
                "chatId=" + chatId + 
                ", question=" + question +
                ", response=" + response + 
                ", evaluation=" + evaluation + 
                ", dailyTraining=" + dailyTraining + 
                ", userId=" + userId +
                '}';
    }
}
