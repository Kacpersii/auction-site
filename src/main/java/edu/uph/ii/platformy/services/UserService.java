package edu.uph.ii.platformy.services;


import edu.uph.ii.platformy.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
// Własne metody
    void save(User user);

    boolean isUniqueLogin(String login);

    User getUser(String username);

    User getUser(Long id);
}
