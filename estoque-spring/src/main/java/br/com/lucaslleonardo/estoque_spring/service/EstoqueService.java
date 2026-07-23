package br.com.lucaslleonardo.estoque_spring.service;


import br.com.lucaslleonardo.estoque_spring.dto.EstoqueDto;
import br.com.lucaslleonardo.estoque_spring.entity.ProdutoEntity;
import br.com.lucaslleonardo.estoque_spring.exception.NaoEncontradoException;
import br.com.lucaslleonardo.estoque_spring.repository.IProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    private IProdutoRepository produtoRepository;

    public void entradaEstoque(Long id, EstoqueDto estoqueDto) throws NaoEncontradoException {

        ProdutoEntity produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setQuantidade(produto.getQuantidade() + estoqueDto.getQuantidade());

        produtoRepository.save(produto);
    }

    public void saidaEstoque(Long id, EstoqueDto estoqueDto) throws NaoEncontradoException {

        ProdutoEntity produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if(produto.getQuantidade() < estoqueDto.getQuantidade()){
            throw (new NaoEncontradoException("Produto com estoque insuficiente"));
        }

        produto.setQuantidade(estoqueDto.getQuantidade()-produto.getQuantidade());
        produtoRepository.save(produto);
    }

}
