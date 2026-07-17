package br.com.lucaslleonardo.estoque_spring.repository;

import br.com.lucaslleonardo.estoque_spring.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface IProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    Optional<ProdutoEntity> findById(Long id);

    Optional<ProdutoEntity> findByNome(String nome);

}
