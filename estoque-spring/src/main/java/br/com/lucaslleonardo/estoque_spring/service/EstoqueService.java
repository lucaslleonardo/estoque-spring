package br.com.lucaslleonardo.estoque_spring.service;


import br.com.lucaslleonardo.estoque_spring.dto.EstoqueDto;
import br.com.lucaslleonardo.estoque_spring.entity.ProdutoEntity;
import br.com.lucaslleonardo.estoque_spring.exception.EstoqueInsuficienteException;
import br.com.lucaslleonardo.estoque_spring.exception.NaoEncontradoException;
import br.com.lucaslleonardo.estoque_spring.repository.IProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final IProdutoRepository produtoRepository;

    public void entradaEstoque(Long id, EstoqueDto estoqueDto) throws NaoEncontradoException {

        ProdutoEntity produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Produto não encontrado"));

        produto.setQuantidade(produto.getQuantidade() + estoqueDto.getQuantidade());

        produtoRepository.save(produto);
    }

    public void saidaEstoque(Long id, EstoqueDto estoqueDto) throws EstoqueInsuficienteException, NaoEncontradoException {

        ProdutoEntity produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Produto não encontrado"));

        if(produto.getQuantidade() < estoqueDto.getQuantidade()){
            throw (new EstoqueInsuficienteException("Produto com estoque insuficiente"));
        }

        produto.setQuantidade(produto.getQuantidade() -  estoqueDto.getQuantidade());

        produtoRepository.save(produto);
    }

}
