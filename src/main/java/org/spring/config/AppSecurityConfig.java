package org.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(registry->
        {
            registry.requestMatchers("/","/courses**","/courses/search","/register","/ingoing","/error","/lead").permitAll()
                    .requestMatchers("/personal/**").hasAuthority("read")
                    .requestMatchers("/control","/control/**").hasAuthority("write");
        })
                .formLogin(custom -> {
                    custom   .usernameParameter("login")
                            .passwordParameter("password")
                            .loginProcessingUrl("/cust-login")
                            .loginPage("/ingoing")
                            .failureHandler((req, resp, exc) ->{
                                req.setAttribute("message", "Неверный логин или пароль");
                                req.getRequestDispatcher("/error").forward(req, resp);
                            } );
                })
                .logout(custom -> {
                    custom  .invalidateHttpSession(true)
                            .logoutUrl("/cust-logout");
                })
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
        .build();
    }
}
