package com.tradinggame.kalmar.model;



import com.tradinggame.kalmar.database.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_NM = "user with name %s not found";
    private final UserRepository user_Repository;

    public UserService(UserRepository user_Repository) {
        this.user_Repository = user_Repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return user_Repository.findByName(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_NM, username)
                        )
                );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers( "/", "/testing", "/registration" )
                .permitAll().anyRequest().authenticated().and().formLogin()
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/testing")
                .permitAll();
        return http.build();
    }



}
