package com.art.portfolio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.art.portfolio.Service.UserDetailsLoader;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

   private UserDetailsLoader usersLoader;

   public SecurityConfiguration(UserDetailsLoader usersLoader) {
      this.usersLoader = usersLoader;
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
   }

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
              /* Login configuration */
              .formLogin()
              .loginPage("/login")
              .defaultSuccessUrl("/profile") // user's home page, it can be any URL
              .permitAll() // Anyone can go to the login page
              /* Logout configuration */
              .and()
              .logout()
              .logoutSuccessUrl("/signin?signout") // append a query string value
              /* Pages that can be viewed without having to log in */
              .and()
              .authorizeHttpRequests()
              .requestMatchers("/", "/profile", "/gallery/**", "/css/**", "/js/**", "/images/**", "/sign-up", "/profile/{username}", "/about") // anyone can see the home and the ads pages
              .permitAll()
              /* Pages that require authentication */
              .and()
              .authorizeHttpRequests()
              .requestMatchers(
                      "/upload", // only authenticated users can create ads
                      "/ads/{id}/edit" // only authenticated users can edit ads
              )
              .authenticated()
      ;
      return http.build();
   }

}
