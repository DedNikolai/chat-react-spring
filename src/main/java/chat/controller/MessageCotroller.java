package chat.controller;

import chat.model.Message;
import chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("messages")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MessageCotroller {
  private final MessageService messageService;

  @GetMapping
  public ResponseEntity<List<Message>> getMessages() {
    List<Message> messages = messageService.getAll();
    return ResponseEntity.ok(messages);
  }

  @PostMapping
  public ResponseEntity<Message> createMessage(@RequestBody Message message) {
    Message created = messageService.createMessage(message);
    return ResponseEntity.ok(created);
  }

  @SendTo("/topic/messages")
  public Message sendMessageToWebSocket(
      @Payload Message chatMessage
  ) {
    return chatMessage;
  }
}
