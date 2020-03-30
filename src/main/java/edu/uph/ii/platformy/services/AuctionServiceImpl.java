package edu.uph.ii.platformy.services;


import edu.uph.ii.platformy.controllers.commands.AuctionFilter;
import edu.uph.ii.platformy.exceptions.AuctionNotFoundException;
import edu.uph.ii.platformy.models.Auction;
import edu.uph.ii.platformy.models.Bid;
import edu.uph.ii.platformy.models.Category;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.AuctionRepository;
import edu.uph.ii.platformy.repositories.CategoryRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuctionRepository auctionRepository;


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Auction> getAllAuctions(AuctionFilter filter, Pageable pageable) {
        Page page;
        if(filter.isEmpty()){
            page = auctionRepository.findAll(pageable);
        }else{
            page = auctionRepository.findAllAuctionsUsingFilter(filter.getTitleLIKE(), filter.getDescriptionLIKE(), pageable);
        }

        return page;
    }

    @Override
    public Page<Auction> getUserAuctions(User seller, Pageable pageable) {
        Page page = auctionRepository.findBySellerOrderByEndDateAsc(seller, pageable);

        return page;
    }

    @Override
    public Page<Auction> getAllSortedAuctions(AuctionFilter filter, Pageable pageable) {
        Page page;
        if(filter.isEmpty()){
            page = auctionRepository.findByOrderByEndDateAsc(pageable);
        }else{
            page = auctionRepository.findAllAuctionsUsingFilter(filter.getTitleLIKE(), filter.getDescriptionLIKE(), pageable);
        }

        return page;
    }

    @Transactional
    @Override
    public Auction getAuction(Long id) {
        Optional<Auction> optionalAuction = auctionRepository.findById(id);
        Auction auction = optionalAuction.orElseThrow(()->new AuctionNotFoundException(id));

        return auction;
    }

    @Override
    public void deleteAuction(Long id) {

        if(auctionRepository.existsById(id) == true){
            auctionRepository.deleteById(id);
        }else{
            throw new AuctionNotFoundException(id);
        }
    }

    @Override
    public void saveAuction(Auction auction) {
        auctionRepository.save(auction);
    }

    @Override
    public void updatePrice(Long id, double price) {
        Auction auction = getAuction(id);
        auction.setPrice(price);

        saveAuction(auction);
    }

    @Override
    public void addBid(Auction auction, Bid bid) {
        Set bids = auction.getBids();
        bids.add(bid);
        auction.setBids(bids);

        saveAuction(auction);
    }
}
