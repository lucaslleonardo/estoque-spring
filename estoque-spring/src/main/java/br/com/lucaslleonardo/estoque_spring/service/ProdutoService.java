package br.com.lucaslleonardo.estoque_spring.service;


import br.com.lucaslleonardo.estoque_spring.dto.ProdutoDto;
import br.com.lucaslleonardo.estoque_spring.entity.CategoriaEntity;
import br.com.lucaslleonardo.estoque_spring.entity.ProdutoEntity;
import br.com.lucaslleonardo.estoque_spring.exception.NaoEncontradoException;
import br.com.lucaslleonardo.estoque_spring.repository.ICategoriaRepository;
import br.com.lucaslleonardo.estoque_spring.repository.IProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final IProdutoRepository produtoRepository;
    private final ICategoriaRepository categoriaRepository;

    public void criarProduto(ProdutoDto produtoDto) throws NaoEncontradoException {
        ProdutoEntity criarProduto = produtoRepository.findByNome(produtoDto.getNome())
                .orElse(null);

        if (criarProduto != null) {
            throw new RuntimeException("Já existe um produto com esse nome.");
        }

        CategoriaEntity categoria = categoriaRepository.findById(produtoDto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        ProdutoEntity registrarProduto = ProdutoEntity.builder()
                .nome(produtoDto.getNome())
                .quantidade(produtoDto.getQuantidade())
                .preco(produtoDto.getPreco())
                .descricao(produtoDto.getDescricao())
                .categoriaEntity(categoria)
                .build();

        produtoRepository.save(registrarProduto);
    }

    public List<ProdutoEntity> listarProdutos() {
        return produtoRepository.findAll();
    }

    public ProdutoEntity getProduto(Long id) throws NaoEncontradoException {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto nao encontrado"));
    }

    public ProdutoEntity atualizarProduto(Long id, ProdutoDto produtoDto)
            throws NaoEncontradoException {

        ProdutoEntity produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Produto não encontrado"));

        produto.setNome(produtoDto.getNome());
        produto.setQuantidade(produtoDto.getQuantidade());
        produto.setPreco(produtoDto.getPreco());
        produto.setDescricao(produtoDto.getDescricao());

        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id) throws NaoEncontradoException {
        produtoRepository.deleteById(id);
    }



}
