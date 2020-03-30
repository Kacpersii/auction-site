package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.Auction;
import edu.uph.ii.platformy.models.Category;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.services.AuctionService;
import edu.uph.ii.platformy.services.UserService;
import lombok.extern.log4j.Log4j2;
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
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes(names = {"auctionTypes",  "auction"})
@Log4j2
public class AuctionFormController {

    private AuctionService auctionService;
    private UserService userService;

    //Wstrzyknięcie zależności przez konstruktor. Od wersji 4.3 Springa nie trzeba używać adnontacji @Autowired, gdy mamy jeden konstruktor
    //@Autowired
    public AuctionFormController(AuctionService auctionService, UserService userService) {
        this.auctionService = auctionService;
        this.userService = userService;
    }


    @RequestMapping(value = "/auctionForm.html", method = RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id) {

        model.addAttribute("auction", id.isPresent() ? auctionService.getAuction(id.get()) : new Auction());

        return "auctionForm";
    }

    @RequestMapping(value = "/auctionForm.html", method = RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String processForm(@Valid @ModelAttribute("auction") Auction a, BindingResult errors, Principal principal) {

        if (errors.hasErrors()) {
            return "auctionForm";
        }

        a.setSeller(userService.getUser(principal.getName()));
        auctionService.saveAuction(a);

        return "redirect:auctionList.html";//po udanym dodaniu/edycji przekierowujemy na listę
    }

    @ModelAttribute("categories")
    public List<Category> loadCategories() {
        List<Category> types = auctionService.getAllCategories();
        return types;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {//Rejestrujemy edytory właściwości

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, false);
        binder.registerCustomEditor(Date.class, "endDate", dateEditor);

        DecimalFormat numberFormat = new DecimalFormat("#0");
        binder.registerCustomEditor(Long.class, "bid.auction.id", new CustomNumberEditor(Long.class, numberFormat, false));
    }
}








