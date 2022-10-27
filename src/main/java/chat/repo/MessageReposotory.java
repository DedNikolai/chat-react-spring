package chat.repo;

import chat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageReposotory extends JpaRepository<Message, Long> {
}
