package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Auction;
import edu.uph.ii.platformy.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

    //nazwa metody jest jednocześnie zapytaniem
    Page<Auction> findByTitleContaining(String phrase, Pageable pageable);

    @Query("SELECT a FROM Auction a WHERE endDate >= now() ORDER BY endDate ASC")
    Page<Auction> findByOrderByEndDateAsc(Pageable pageable);

    Page<Auction> findBySellerOrderByEndDateAsc(User seller, Pageable pageable);

    //nad klasą Vehicle znajduje się definicja zapytania (@NamedQuery) powiązana z tą metodą
    Page<Auction> findAllAuctionsUsingNamedQuery(String phrase, Pageable pageable);

    @Query("SELECT a FROM Auction a WHERE " +
            "( :title is null OR :title = '' OR "+
            "upper(a.title) LIKE upper(:title)) " +
            "AND " +
            "(:description is null OR :description = '' OR "+
            "upper(a.description) LIKE upper(:description))" +
            "AND " +
            "(:category is null OR :category = '' OR "+
            "upper(a.category.name) LIKE upper(:category)) " +
            "ORDER BY endDate ASC")
    Page<Auction> findAllAuctionsUsingFilter(@Param("title") String title, @Param("description") String description, @Param("category") String category, Pageable pageable);

}

