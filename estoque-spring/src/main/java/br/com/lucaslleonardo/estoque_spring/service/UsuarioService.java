package br.com.lucaslleonardo.estoque_spring.service;


import br.com.lucaslleonardo.estoque_spring.dto.UsuarioDto;
import br.com.lucaslleonardo.estoque_spring.entity.UsuarioEntity;
import br.com.lucaslleonardo.estoque_spring.handler.NaoEncontradoException;
import br.com.lucaslleonardo.estoque_spring.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public void criarUsuario(UsuarioDto usuarioDto) throws NaoEncontradoException {

        UsuarioEntity cadastrarUsuario = usuarioRepository.findByEmail(usuarioDto.getEmail())
                .orElse(null);

        if (cadastrarUsuario != null) {
            throw new RuntimeException("Usuario já cadastrado");
        }

        UsuarioEntity criarUsuario = UsuarioEntity.builder()
                .usuario(usuarioDto.getUsuario())
                .email(usuarioDto.getEmail())
                .senha(usuarioDto.getSenha())
                .cargos(usuarioDto.getCargos())
                .build();

        usuarioRepository.save(criarUsuario);
    }

    public void alterarUsuario(Long id, UsuarioDto usuarioDto) throws NaoEncontradoException {

        UsuarioEntity attUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encotrado"));

        attUsuario.setEmail(usuarioDto.getEmail());
        attUsuario.setSenha(usuarioDto.getSenha());
        attUsuario.setCargos(usuarioDto.getCargos());
        usuarioRepository.save(attUsuario);

    }

    public void deletarUsuario(Long id) throws NaoEncontradoException {
        usuarioRepository.deleteById(id);
    }

}
