package edu.uph.ii.platformy.config;

import edu.uph.ii.platformy.models.Bid;
import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer {

    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private OpinionRepository opinionRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    InitializingBean init() {

        return () -> {

            if (categoryRepository.findAll().isEmpty() && roleRepository.findAll().isEmpty() == true) {//przyjmijmy, że jeśli repozytorium typów jest puste, to trzeba zainicjalizować bazę danych

                Role roleUser = roleRepository.save(new Role(Role.Types.ROLE_USER));
                Role roleAdmin = roleRepository.save(new Role(Role.Types.ROLE_ADMIN));

                User user1 = new User("user", true);
                user1.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                user1.setPassword(passwordEncoder.encode("user"));

                User user2 = new User("jan", true);
                user2.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                user2.setPassword(passwordEncoder.encode("jan"));

                User user3 = new User("adam", true);
                user3.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                user3.setPassword(passwordEncoder.encode("adam"));

                User admin = new User("admin", true);
                admin.setRoles(new HashSet<>(Arrays.asList(roleUser, roleAdmin)));
                admin.setPassword(passwordEncoder.encode("admin"));

                userRepository.save(user1);
                userRepository.save(user2);
                userRepository.save(user3);
                userRepository.save(admin);

                Category category1 = new Category("Elektronika");
                Category category2 = new Category("Motoryzacja");
                Category category3 = new Category("Kultura i rozrywka");
                Category category4 = new Category("Sport");
                categoryRepository.saveAndFlush(category1);
                categoryRepository.saveAndFlush(category2);
                categoryRepository.saveAndFlush(category3);
                categoryRepository.saveAndFlush(category4);

                Auction auction1 = new Auction("Huawei lepsze", "HUAWEI P30 Lite został wyposażony w aż 3 tylnie obiektywy wspomagane przez sztuczną inteligencję AI. ","1.jpg", 1651,555555555, new Date(120, 02, 31, 12, 01, 01), category1, user1);
                Auction auction2 = new Auction("Laptop HP Elitebook", "Laptop HP Elitebook x360 830 G6 i5-8265U/Touch13,3FHD/8GB/256SSD/W10P","2.jpg",2400,222222222,  new Date(120, 02, 31, 13, 01, 01), category1, user3);
                Auction auction3 = new Auction("BMW X6M 600KM", "Rok produkcji: 05/2015 asdasasd","3.jpg",125000,999999999,  new Date(120, 02, 31, 17, 01, 01), category2, user1);
                Auction auction4 = new Auction("Skuter", "Bardzo fajny skuter. Polecam!","4.jpg", 4000,888888888, new Date(120, 02, 31, 15, 21, 01), category2, user3);
                Auction auction5 = new Auction("Książka", "Jakaś książka nie wiem jaka znalazłem na strychu chyba stara","5.jpg", 15,777777777, new Date(120, 02, 31, 13, 01, 01), category3, user2);
                Auction auction6 = new Auction("Pan Tadeusz", "ksiazka nie wudka hehe","6.jpg", 16.50,666666666, new Date(120, 03, 01, 17, 01, 01), category3, user2);
                Auction auction7 = new Auction("Spalding TF-250", "Piłka do kosza Spalding porządna prawie jak nowa używana tylko raz","7.jpg", 69.99,333333333, new Date(120, 03, 02, 18, 01, 01), category4, user1);
                Auction auction8 = new Auction("Adidas Predator r. 44", "Koreczki adidas predator rozmiar 44 mało co używane. Sprzedaję bo na mnie są za duże","8.jpg", 330,444444444, new Date(120, 03, 02, 19, 01, 01), category4, user3);

                System.out.println(auction4.getEndDate());

                auctionRepository.saveAndFlush(auction1);
                auctionRepository.saveAndFlush(auction2);
                auctionRepository.saveAndFlush(auction3);
                auctionRepository.saveAndFlush(auction4);
                auctionRepository.saveAndFlush(auction5);
                auctionRepository.saveAndFlush(auction6);
                auctionRepository.saveAndFlush(auction7);
                auctionRepository.saveAndFlush(auction8);

            }

            if (bidRepository.findAll().isEmpty()) {
                Bid bid1 = new Bid(userRepository.getOne((long) 1), auctionRepository.getOne((long) 1), 1550, new Date(120, 02, 30, 11, 47, 06));
                Bid bid2 = new Bid(userRepository.getOne((long) 2), auctionRepository.getOne((long) 1), 1600, new Date(120, 02, 30, 11, 51, 55));
                Bid bid3 = new Bid(userRepository.getOne((long) 1), auctionRepository.getOne((long) 1), 1650, new Date(120, 02, 30, 12, 06, 01));
                Bid bid4 = new Bid(userRepository.getOne((long) 3), auctionRepository.getOne((long) 1), 1651, new Date(120, 02, 30, 12, 11, 45));

                bidRepository.saveAndFlush(bid1);
                bidRepository.saveAndFlush(bid2);
                bidRepository.saveAndFlush(bid3);
                bidRepository.saveAndFlush(bid4);
            }

            if (opinionRepository.findAll().isEmpty()) {
                Opinion opinion1 = new Opinion("Nie polecam tego, bardzo słaba jakość", auctionRepository.getOne((long) 1), userRepository.getOne((long) 1), new Date(120, 02, 29, 13, 47, 06));

                opinionRepository.saveAndFlush(opinion1);
            }
        };
    }
}
