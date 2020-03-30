package edu.uph.ii.platformy.services;


import edu.uph.ii.platformy.models.Auction;
import edu.uph.ii.platformy.models.Opinion;
import edu.uph.ii.platformy.repositories.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionRepository opinionRepository;

    @Override
    public List<Opinion> getAllAuctionOpinions(Auction auction) {
        return opinionRepository.findByAuctionOrderByDateAsc(auction);
    }

    @Override
    public Opinion getOpinion(Long id) {
        return opinionRepository.findOpinionById(id);
    }

    @Override
    public void deleteOpinion(Long id) {
        opinionRepository.deleteById(id);
    }

    @Override
    public void saveOpinion(Opinion opinion) {
        opinionRepository.save(opinion);
    }
}
