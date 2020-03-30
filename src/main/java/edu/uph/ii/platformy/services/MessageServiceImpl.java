package edu.uph.ii.platformy.services;


import edu.uph.ii.platformy.models.Auction;
import edu.uph.ii.platformy.models.Message;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository MessageRepository;

    @Override
    public List<Message> getAllSenderMessages(User user) {
        return MessageRepository.findBySenderOrderByDateAsc(user);
    }

    @Override
    public List<Message> getAllReceiverMessages(User user) {
        return MessageRepository.findByReceiverOrderByDateAsc(user);
    }

    @Override
    public Message getMessage(Long id) {
        return MessageRepository.findMessageById(id);
    }

    @Override
    public void deleteMessage(Long id) {
        MessageRepository.deleteById(id);
    }

    @Override
    public void saveMessage(Message message) {
        MessageRepository.save(message);
    }
}
