package br.com.lucaslleonardo.estoque_spring.Controller;


import br.com.lucaslleonardo.estoque_spring.dto.UsuarioDto;
import br.com.lucaslleonardo.estoque_spring.exception.NaoEncontradoException;
import br.com.lucaslleonardo.estoque_spring.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarUsuario(@Valid @RequestBody UsuarioDto usuarioDto) throws NaoEncontradoException {
        usuarioService.criarUsuario(usuarioDto);
    }

    @PutMapping("/{id}/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarUsuario(@Valid @RequestBody UsuarioDto usuarioDto, @PathVariable Long id) throws NaoEncontradoException {
        usuarioService.alterarUsuario( id ,usuarioDto);
    }

    @DeleteMapping("/{id}/deletar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirUsuario(@PathVariable Long id) throws NaoEncontradoException {
        usuarioService.deletarUsuario(id);
    }

}
