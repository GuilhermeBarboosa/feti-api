package br.com.codiub.feti.core.config;

import br.com.codiub.feti.api.service.JwtService;
import br.com.codiub.feti.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class JwtFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Bean
    public OncePerRequestFilter init() {
        return new JwtAuthFilter(jwtService, userService);
    }
}
