package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Auction;
import edu.uph.ii.platformy.models.Opinion;

import java.util.List;

public interface OpinionService {

    List<Opinion> getAllAuctionOpinions(Auction auction);

    Opinion getOpinion(Long id);

    void deleteOpinion(Long id);

    void saveOpinion(Opinion opinion);
}
