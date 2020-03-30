package edu.uph.ii.platformy.services;


import edu.uph.ii.platformy.controllers.commands.AuctionFilter;
import edu.uph.ii.platformy.models.Auction;
import edu.uph.ii.platformy.models.Bid;
import edu.uph.ii.platformy.models.Category;
import edu.uph.ii.platformy.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuctionService {

    List<Category> getAllCategories();

    Page<Auction> getAllAuctions(AuctionFilter filter, Pageable pageable);

    Page<Auction> getUserAuctions(User seller, Pageable pageable);

    Page<Auction> getAllSortedAuctions(AuctionFilter filter, Pageable pageable);

    Auction getAuction(Long id);

    void deleteAuction(Long id);

    void saveAuction(Auction auction);

    void updatePrice(Long id, double price);

    void addBid(Auction auction, Bid bid);
}
