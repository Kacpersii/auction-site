package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Message;
import edu.uph.ii.platformy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySenderOrderByDateAsc(User user);

    List<Message> findByReceiverOrderByDateAsc(User user);

    @Query("SELECT m FROM Message m WHERE :id = m.id")
    Message findMessageById(@Param("id") Long id);
}
