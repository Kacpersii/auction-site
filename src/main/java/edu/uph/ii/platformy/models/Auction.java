package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "auctions")
@NamedQuery(name = "Auction.findAllAuctionsUsingNamedQuery",
        query = "SELECT a FROM Auction a WHERE upper(a.title) LIKE upper(:phrase) or upper(a.description) LIKE upper(:phrase) ")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    //@Size(min = 2, max = 30)
    @Length(min = 2, max = 100)
    private String title;

    private String description;

    @Size(min = 1, max = 50)
    private String path;

    @Positive
    private double price;

    private int phone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Bid> bids;

    public Auction() {
        this.category = new Category();
    }

    public Auction(String title, String description, String path, double price, int phone, Date endDate, Category category, User seller) {
        this.title = title;
        this.description = description;
        this.path = path;
        this.price = price;
        this.phone = phone;
        this.endDate = endDate;
        this.category = category;
        this.seller = seller;
    }
}
