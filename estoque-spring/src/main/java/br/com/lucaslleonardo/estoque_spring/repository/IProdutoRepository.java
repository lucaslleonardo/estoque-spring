package br.com.lucaslleonardo.estoque_spring.repository;

import br.com.lucaslleonardo.estoque_spring.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    Optional<ProdutoEntity> findById(Long id);
}
