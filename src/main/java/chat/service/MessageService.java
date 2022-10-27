package chat.service;

import chat.model.Message;
import chat.repo.MessageReposotory;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
  private final MessageReposotory messageReposotory;
  private final SimpMessagingTemplate messagingTemplate;

  public List<Message> getAll() {
    return messageReposotory.findAll();
  }

  public Message createMessage(Message message) {
    Message createdMessage = messageReposotory.save(message);
    messagingTemplate.convertAndSend(String.format("/topic/messages"), message);
    return createdMessage;
  }
}
