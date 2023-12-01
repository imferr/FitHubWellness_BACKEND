package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.ChatGptResponseBL;
import bo.edu.ucb.fithubwelness.dto.ChatGptResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chatgpt")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatGptResponseAPI {

    private final ChatGptResponseBL chatGptResponseBL;

    @Autowired
    public ChatGptResponseAPI(ChatGptResponseBL chatGptResponseBL) {
        this.chatGptResponseBL = chatGptResponseBL;
    }

    @PostMapping("/interact/user/{userId}/evaluation/{evaluationId}/dailytraining/{dailyTrainingId}")
    public ResponseEntity<ChatGptResponseDTO> interactWithChatGpt(@PathVariable int userId,
            @PathVariable int evaluationId,
            @PathVariable int dailyTrainingId,
            @RequestBody InteractionRequest request) {
        ChatGptResponseDTO response = chatGptResponseBL.interactWithChatGpt(
                userId, evaluationId, dailyTrainingId, request.question());
        return ResponseEntity.ok(response);
        // en esta funcion se debe llamar al servicio de chatgpt y retornar la respuesta
    }

    @GetMapping("/conversations/{userId}")
    public ResponseEntity<List<ChatGptResponseDTO>> getConversationsByUserId(@PathVariable int userId) {
        List<ChatGptResponseDTO> conversations = chatGptResponseBL.getConversationsByUserId(userId);
        return ResponseEntity.ok(conversations);
        // en esta funcion se retorna la lista de conversaciones
    }

    private record InteractionRequest(String question) {
    } // esta clase se usa para recibir el json de la peticion
}
