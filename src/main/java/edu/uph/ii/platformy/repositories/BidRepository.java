package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Auction;
import edu.uph.ii.platformy.models.Bid;
import edu.uph.ii.platformy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findByBidderOrderByAddDateDesc(User user);

    List<Bid> findByAuctionOrderByAddDateDesc(Auction auction);
}
