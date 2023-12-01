package bo.edu.ucb.fithubwelness.dto;

import java.util.List;

public class ChatResponse {

    private List<Choice> choices;

    // Constructor vacío
    public ChatResponse() {
    }

    // Constructor con parámetros
    public ChatResponse(List<Choice> choices) {
        this.choices = choices;
    }

    // Getters y setters
    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    // Clase interna Choice
    public static class Choice {

        private int index;
        private Message message;

        // Constructor vacío
        public Choice() {
        }

        // Constructor con parámetros
        public Choice(int index, Message message) {
            this.index = index;
            this.message = message;
        }

        // Getters y setters
        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }
    }
}
