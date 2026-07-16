package br.com.lucaslleonardo.estoque_spring.repository;

import br.com.lucaslleonardo.estoque_spring.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findById(Long id);
    Optional<UsuarioEntity> findByEmail(String email);

}
