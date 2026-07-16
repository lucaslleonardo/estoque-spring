package br.com.lucaslleonardo.estoque_spring.repository;

import br.com.lucaslleonardo.estoque_spring.dto.CategoriaDto;
import br.com.lucaslleonardo.estoque_spring.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    Optional<CategoriaEntity> findById(Long id);

}
