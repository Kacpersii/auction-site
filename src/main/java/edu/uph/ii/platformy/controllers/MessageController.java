package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Auction;
import edu.uph.ii.platformy.models.Message;
import edu.uph.ii.platformy.models.Opinion;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.services.AuctionService;
import edu.uph.ii.platformy.services.MessageService;
import edu.uph.ii.platformy.services.OpinionService;
import edu.uph.ii.platformy.services.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MessageController {

    protected final Log log = LogFactory.getLog(getClass());//Dodatkowy komponent do logowania

    @Autowired
    AuctionService auctionService;

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    OpinionService opinionService;

    @RequestMapping(value="/processMessage.html", method = RequestMethod.POST)
    public String processMessageForm(@Valid @ModelAttribute("message") Message message, Model model, BindingResult errors, Principal principal) {
        if (errors.hasErrors()) {
            log.info("errors.hasErrors()");

            return "auctionDetails";
        }

        User sender = userService.getUser(principal.getName());
        User receiver = userService.getUser(message.getAuctionM().getSeller().getId());
        message.setSender(sender);
        message.setReceiver(receiver);
        messageService.saveMessage(message);

        Auction auction = message.getAuctionM();
        List<Opinion> opinions = opinionService.getAllAuctionOpinions(auction);

        Opinion newOpinion = new Opinion();
        newOpinion.setAuction(auction);
        Message newMessage = new Message();
        newMessage.setAuctionM(auction);

        model.addAttribute("auction", auction);
        model.addAttribute("opinion", newOpinion);
        model.addAttribute("opinions", opinions);
        model.addAttribute("message", newMessage);

        return "auctionDetails";
    }

    @RequestMapping(value="/messages.html", method = RequestMethod.GET)
    public String showMessages(Model model, Principal principal){
        User user = userService.getUser(principal.getName());
        List<Message> receivedMessages = messageService.getAllReceiverMessages(user);
        List<Message> sendedMessages = messageService.getAllSenderMessages(user);


        model.addAttribute("receivedMessages", receivedMessages);
        model.addAttribute("sendedMessages", sendedMessages);

        return "messages";
    }

}
