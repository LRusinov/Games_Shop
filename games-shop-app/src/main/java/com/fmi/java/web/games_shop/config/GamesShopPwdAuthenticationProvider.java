package com.fmi.java.web.games_shop.config;

import com.fmi.java.web.games_shop.dto.ClientDTO;
import com.fmi.java.web.games_shop.model.Client;
import com.fmi.java.web.games_shop.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class GamesShopPwdAuthenticationProvider implements AuthenticationProvider {

    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        ClientDTO clientDTO = clientService.findClientByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("No user registered with this credentials!"));

        if (!passwordEncoder.matches(password, clientDTO.password())) {
            throw new BadCredentialsException("Invalid password.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(clientDTO.role().name()));
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
