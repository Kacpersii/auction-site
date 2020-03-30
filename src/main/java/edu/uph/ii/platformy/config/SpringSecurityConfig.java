package edu.uph.ii.platformy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = passwordEncoder();
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("user").password(encoder.encode("user")).roles("USER");
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("admin").password(encoder.encode("admin")).roles("ADMIN");
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("useradmin").password(encoder.encode("useradmin")).roles("USER","ADMIN");
    }
*/



    @Bean
    @Profile(ProfileNames.INMEMORY)
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        User.UserBuilder userBuilder = User.builder();

        UserDetails user = userBuilder
                .username("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build();

        UserDetails admin = userBuilder
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails test = userBuilder
                .username("useradmin")
                .password(passwordEncoder.encode("useradmin"))
                .roles("USER", "ADMIN")
                .build();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(user);
        manager.createUser(admin);
        manager.createUser(test);

        return manager;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/statics/**", "/webjars/**", "/", "/auctionList.html", "/registrationForm.html")
                .permitAll()//do powyższych zasobów ma mieć każdy
                .antMatchers( "/auctionForm.html", "/makeBid").hasAnyRole("ADMIN", "USER")
                .anyRequest()
                .permitAll();//pozostałe żądania mają być uwierzytelnione

        http
                .formLogin()//pozwól użytkownikom uwierzytelniać się poprzez formularz logowania
                .loginPage("/login")//formularz dostępny jest pod URL
                .permitAll();//pozwól każdemu się zalogować.

        http
                .formLogin()
                .defaultSuccessUrl("/auctionList.html");

        http.logout().permitAll();//pozwól każdemu się wylogować
    }



}

