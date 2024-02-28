package com.tinesh_blogs.BlogApplication.springSecurity;

import ch.qos.logback.core.encoder.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SpringSecurityConfiguration {
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager(){
        UserDetails userDetails1 = createNewUser("thiru","olu") ;
        UserDetails userDetails2 = createNewUser("sudar","oli") ;
        return new InMemoryUserDetailsManager(userDetails1,userDetails2) ;
    }

        private UserDetails createNewUser(String username, String passcode) {
        Function<String,String> passwordEncoder = input->passwordEncoder().encode(input) ;
        UserDetails userDetails = User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(passcode)
                .roles("USER","ADMIN")
                .build() ;
        return userDetails ;
    }

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() ;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth->auth.anyRequest().authenticated()) ;
        http.formLogin(Customizer.withDefaults()) ;
        http.csrf(csrf->csrf.disable()) ;
        http.headers(header->header.frameOptions(frameOption->frameOption.sameOrigin())) ;
        return http.build() ;
    }

}
