package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Auction;
import edu.uph.ii.platformy.models.Bid;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.services.AuctionService;
import edu.uph.ii.platformy.services.BidService;
import edu.uph.ii.platformy.services.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes(names = {"auctionTypes",  "auction"})
public class BidController {

    protected final Log log = LogFactory.getLog(getClass());//Dodatkowy komponent do logowania

    @Autowired
    private BidService bidService;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private UserService userService;

    @RequestMapping(value="/makeBid.html", method = RequestMethod.POST)
    public String showBidForm(Model model, long id){
        Auction auction = auctionService.getAuction(id);
        Bid bid = new Bid();
        bid.setAuction(auction);
        bid.setAmount(auction.getPrice());

        model.addAttribute("auction", auction);
        model.addAttribute("bid", bid);
        model.addAttribute("bidsList", bidService.getAuctionsBids(auction));

        return "makeBid";
    }

    @RequestMapping(value="/processBid.html", method = RequestMethod.POST)
    public String processBidForm(@Valid @ModelAttribute("bid") Bid bid, Model model, BindingResult errors, Principal principal) {
        if (errors.hasErrors()) {
            log.info("errors.hasErrors()");

            return "makeBid";
        }

        User user = userService.getUser(principal.getName());
        bid.setBidder(user);
        bidService.saveBid(bid);
        Auction auction = auctionService.getAuction(bid.getAuction().getId());
        auction.setPrice(bid.getAmount());
        auctionService.addBid(auction, bid);
        auctionService.saveAuction(auction);

        bid.setAuction(auction);
        bidService.saveBid(bid);

        model.addAttribute("auction", auction);
        model.addAttribute("bid", bid);
        model.addAttribute("bidsList", bidService.getAuctionsBids(auction));

        return "makeBid";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {//Rejestrujemy edytory właściwości
        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, false);
        binder.registerCustomEditor(Date.class, "addDate", dateEditor);

        binder.registerCustomEditor(Double.class, "amount", new CustomNumberEditor(Float.class, numberFormat, false));
    }
}