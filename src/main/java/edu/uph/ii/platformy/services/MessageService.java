package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Message;
import edu.uph.ii.platformy.models.User;

import java.util.List;

public interface MessageService {

    List<Message> getAllSenderMessages(User user);

    List<Message> getAllReceiverMessages(User user);

    Message getMessage(Long id);

    void deleteMessage(Long id);

    void saveMessage(Message message);
}
