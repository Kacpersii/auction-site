package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Auction;
import edu.uph.ii.platformy.models.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {

    List<Opinion> findByAuctionOrderByDateAsc(Auction auction);

    @Query("SELECT o FROM Opinion o WHERE :id = o.id")
    Opinion findOpinionById(@Param("id") Long id);
}
