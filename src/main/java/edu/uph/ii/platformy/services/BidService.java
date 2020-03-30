package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Auction;
import edu.uph.ii.platformy.models.Bid;
import edu.uph.ii.platformy.models.User;

import java.util.List;

public interface BidService {

    List<Bid> getAllBids();

    List<Bid> getUsersBids(User user);

    List<Bid> getAuctionsBids(Auction auction);

    Bid getBid(Long id) throws Exception;

    void saveBid(Bid bid);

    //Bid getHighestBid(Auction auction);
}
