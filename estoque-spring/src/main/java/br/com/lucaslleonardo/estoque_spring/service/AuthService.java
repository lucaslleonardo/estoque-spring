package br.com.lucaslleonardo.estoque_spring.service;

import br.com.lucaslleonardo.estoque_spring.Config.TokenProvider;
import br.com.lucaslleonardo.estoque_spring.dto.LoginRequest;
import br.com.lucaslleonardo.estoque_spring.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Value("${jwt.expiration}")
    private Long expiration;

    public TokenResponse login(LoginRequest loginRequest) throws Exception {

        try {

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginRequest.email(),
                                    loginRequest.password()
                            )
                    );

            UserDetails userDetails =
                    (UserDetails) authentication.getPrincipal();

            String token = tokenProvider.generateToken(userDetails);

            return new TokenResponse(token, expiration);

        } catch (BadCredentialsException e) {
            throw new Exception("Credenciais inválidas");
        }

    }
}