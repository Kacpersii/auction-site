package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.exceptions.BidNotFoundException;
import edu.uph.ii.platformy.models.Auction;
import edu.uph.ii.platformy.models.Bid;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidServiceImpl implements BidService {

    @Autowired
    private BidRepository bidRepository;

    @Override
    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    @Override
    public List<Bid> getUsersBids(User user) {
        return bidRepository.findByBidderOrderByAddDateDesc(user);
    }

    @Override
    public List<Bid> getAuctionsBids(Auction auction) {
        return bidRepository.findByAuctionOrderByAddDateDesc(auction);
    }

    @Override
    public Bid getBid(Long id){
        Optional<Bid> optionalBid = bidRepository.findById(id);
        Bid bid = optionalBid.orElseThrow(()->new BidNotFoundException());
        return bid;
    }

    @Override
    public void saveBid(Bid bid) {
        bidRepository.save(bid);
    }
//
//    @Override
//    public Bid getHighestBid(Auction auction){
//        List<Bid> bids = bidRepository.findAuctionsBids(auction);
//
//        Bid maxBid = bids.get(0);
//
//        for(Bid bid : bids) {
//            if(bid.getAmount() >= maxBid.getAmount()) {
//                maxBid = bid;
//            }
//        }
//
//        return maxBid;
//    }

}
