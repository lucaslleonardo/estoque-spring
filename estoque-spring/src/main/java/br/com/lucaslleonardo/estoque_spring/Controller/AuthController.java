package br.com.lucaslleonardo.estoque_spring.Controller;

import br.com.lucaslleonardo.estoque_spring.dto.LoginRequest;
import br.com.lucaslleonardo.estoque_spring.dto.TokenResponse;
import br.com.lucaslleonardo.estoque_spring.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        return authService.login(loginRequest);
    }
}