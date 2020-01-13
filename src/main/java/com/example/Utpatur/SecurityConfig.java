package com.example.Utpatur;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
              /*  .authorizeRequests()
                .antMatchers("/").permitAll()
                */

                .authorizeRequests()
                    .antMatchers("/", "/kartvy", "/registrering", "/tur", "/style.css", "/index.js", "/map-route.js", "mapview.js").permitAll()
                .antMatchers("/static/**").permitAll()
                    .antMatchers("/profil", "/skapa-ny-tur").hasRole("USER")
                .anyRequest().authenticated()

                .and()

                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/profil", true);





    }


    @Bean
    public UserDetailsService userDetailsService () {
        //creating a new user in memory and therefore overriding the userDetailsService
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("Josse").password("abc").roles("USER").build());
        return manager;
    }




}
