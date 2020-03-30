package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "opinions")
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auction_id", nullable = false)
    private Auction auction;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "evaluative_id", nullable = false)
    private User evaluative;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Opinion(String description, Auction auction, User evaluative, Date date) {
        this.description = description;
        this.auction = auction;
        this.evaluative = evaluative;
        this.date = date;
    }
}
