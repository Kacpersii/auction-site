package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.controllers.commands.AuctionFilter;
import edu.uph.ii.platformy.models.Auction;
import edu.uph.ii.platformy.models.Opinion;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.services.AuctionService;
import edu.uph.ii.platformy.services.BidService;
import edu.uph.ii.platformy.services.OpinionService;
import edu.uph.ii.platformy.services.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;


@Controller
@SessionAttributes("searchCommand")
public class AuctionListController {

    protected final Log log = LogFactory.getLog(getClass());//Dodatkowy komponent do logowania

    @Autowired
    AuctionService auctionService;

    @Autowired
    OpinionService opinionService;

    @Autowired
    UserService userService;

    @Autowired
    BidService bidService;

    @RequestMapping(value="/auctionList.html", params = "id", method = RequestMethod.GET)
    public String showAuctionDetails(Model model, long id){
        Auction auction = auctionService.getAuction(id);

        List<Opinion> opinions = opinionService.getAllAuctionOpinions(auction);

        model.addAttribute("auction", auction);
        model.addAttribute("opinions", opinions);

        return "auctionDetails";
    }

    @ModelAttribute("searchCommand")
    public AuctionFilter getSimpleSearch(){
        return new AuctionFilter();
    }

    @GetMapping(value="/auctionList.html", params = {"all"})
    public String resetAuctionList(@ModelAttribute("searchCommand") AuctionFilter search){
        search.clear();
        return "redirect:auctionList.html";
    }

    @RequestMapping(value="/auctionList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showAuctionList(Model model, Pageable pageable, @Valid @ModelAttribute("searchCommand") AuctionFilter search, BindingResult result){
        model.addAttribute("auctionListPage", auctionService.getAllSortedAuctions(search, pageable));
        return "auctionList";
    }

    @RequestMapping(value="/userAuctionsList.html", method = {RequestMethod.GET})
    public String showUserAuctionsList(Model model, Pageable pageable, Optional<Long> id, Principal principal){
        User user = id.isPresent() ? userService.getUser(id.get()) : userService.getUser(principal.getName());
        Page<Auction> auctions = auctionService.getUserAuctions(user, pageable);
        model.addAttribute("auctionListPage", auctions);

        boolean isloggedUserAuctions = id.isPresent() ? false : true;
        model.addAttribute("loggedUserAuctions", isloggedUserAuctions);

        String whoseAuctions = id.isPresent() ? "Aukcje użytkownika " + auctions.getContent().get(0).getSeller().getUsername() : "Twoje aukcje";
        model.addAttribute("whoseAuctions", whoseAuctions);

        return "userAuctionsList";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(path="/auctionList.html", params={"did"})
    public String deleteAuction(long did, HttpServletRequest request){
        log.info("Usuwanie aukcji o id "+did);
        auctionService.deleteAuction(did);
        String queryString = prepareQueryString(request.getQueryString());
        return String.format("redirect:auctionList.html?%s", queryString);//robimy przekierowanie, ale zachowując parametry pageingu
    }

    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        return queryString.substring(queryString.indexOf("&")+1);//obcinamy parametr did, bo inaczej znowu będzie wywołana metoda deleteVihicle
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {//Rejestrujemy edytory właściwości
        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
        CustomNumberEditor priceEditor = new CustomNumberEditor(Float.class, numberFormat, true);
        binder.registerCustomEditor(Float.class, "minPrice", priceEditor);
        binder.registerCustomEditor(Float.class, "maxPrice", priceEditor);
    }

}
