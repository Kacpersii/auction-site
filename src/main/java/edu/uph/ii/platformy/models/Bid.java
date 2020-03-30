package edu.uph.ii.platformy.models;

import edu.uph.ii.platformy.models.Auction;
import edu.uph.ii.platformy.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@Entity
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bidder_id", nullable = false)
    private User bidder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auction_id", nullable = false)
    private Auction auction;

    @Positive
    private double amount;

    @Column(name="add_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;

    public Bid(User bidder, Auction auction, double amount, Date addDate) {
        this.bidder = bidder;
        this.auction = auction;
        this.amount = amount;
        this.addDate = addDate;
    }

    public Bid() {
        this.addDate = new Date();
    }
}
