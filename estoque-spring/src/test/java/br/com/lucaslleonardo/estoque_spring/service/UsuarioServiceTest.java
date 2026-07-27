package br.com.lucaslleonardo.estoque_spring.service;

import br.com.lucaslleonardo.estoque_spring.dto.UsuarioDto;
import br.com.lucaslleonardo.estoque_spring.entity.UsuarioEntity;
import br.com.lucaslleonardo.estoque_spring.exception.JaExisteException;
import br.com.lucaslleonardo.estoque_spring.exception.NaoEncontradoException;
import br.com.lucaslleonardo.estoque_spring.repository.IUsuarioRepository;
import br.com.lucaslleonardo.estoque_spring.roles.Cargos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    IUsuarioRepository usuarioRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    private UsuarioEntity usuario;
    private UsuarioDto usuarioDto;

    @BeforeEach
    void setUp() {


        usuario = UsuarioEntity.builder()
                .id(1L)
                .usuario("UsuarioTest")
                .email("usuario@email.com")
                .senha("SenhaTest123")
                .cargos(Cargos.ADMIN)
                .build();

        usuarioDto = UsuarioDto.builder()

                .usuario("UsuarioTest")
                .email("usuario@email.com")
                .senha("SenhaTest123")
                .cargos(Cargos.ADMIN)
                .build();


    }


    @Test
    @DisplayName("Teste para criar usuario")
    void deveCriarUsuario() {

        when(passwordEncoder.encode(usuarioDto.getSenha())).thenReturn("senhaCriptografada");

        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.empty());
        usuarioService.criarUsuario(usuarioDto);
        verify(usuarioRepository).save(any(UsuarioEntity.class));
    }

    @Test
    @DisplayName("Teste para alterar usuário")
    void deveAlterarUsuario() throws NaoEncontradoException {
        when(passwordEncoder.encode(usuarioDto.getSenha())).thenReturn("senhaCriptografada");
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));

        usuarioService.alterarUsuario(usuario.getId(), usuarioDto);
        verify(usuarioRepository).save(any(UsuarioEntity.class));
    }

    @Test
    @DisplayName("Teste apagar usuario")
    void deveApagarUsuario(){
        usuarioService.deletarUsuario(usuario.getId());
        verify(usuarioRepository).deleteById(usuario.getId());
    }

    @Test
    @DisplayName("Teste exception usuário já cadastrado")
    void deveLancarJaExisteException() {
        when(usuarioRepository.findByEmail(usuarioDto.getEmail())).thenReturn(Optional.of(usuario));
        assertThrows(JaExisteException.class, () -> usuarioService.criarUsuario(usuarioDto));
    }

    @Test
    @DisplayName("Teste usuário não encontrado ao alterar")
    void deveMostrarExceptionUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> usuarioService.alterarUsuario(usuario.getId(), usuarioDto));
    }


}