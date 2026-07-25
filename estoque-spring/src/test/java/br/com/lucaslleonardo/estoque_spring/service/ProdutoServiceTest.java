package br.com.lucaslleonardo.estoque_spring.service;

import br.com.lucaslleonardo.estoque_spring.dto.ProdutoDto;
import br.com.lucaslleonardo.estoque_spring.entity.CategoriaEntity;
import br.com.lucaslleonardo.estoque_spring.entity.ProdutoEntity;
import br.com.lucaslleonardo.estoque_spring.exception.JaExisteException;
import br.com.lucaslleonardo.estoque_spring.exception.NaoEncontradoException;
import br.com.lucaslleonardo.estoque_spring.repository.ICategoriaRepository;
import br.com.lucaslleonardo.estoque_spring.repository.IProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    //ARRANGE

    @Mock
    private IProdutoRepository produtoRepository;

    @Mock
    private ICategoriaRepository categoriaRepository;

    @InjectMocks
    private ProdutoService produtoService;

    private ProdutoEntity produto1;

    private ProdutoEntity produto2;

    private ProdutoDto produtoDto;

    private CategoriaEntity categoria1;

    @BeforeEach
    void setUp() {

        produtoDto = ProdutoDto.builder()
                .nome("produtoDto")
                .quantidade(5)
                .preco(5.5)
                .descricao("DescricaoDto")
                .categoriaId(1l)
                .build();

        produto1 = ProdutoEntity.builder()
                .id(1l)
                .nome("Detergente")
                .preco(5.7)
                .descricao("ProdutoLimpeza")
                .build();

        produto2 = ProdutoEntity.builder()
                .nome("Chocolate")
                .preco(9.5)
                .descricao("Chocolate Amargo")
                .build();

        categoria1 = CategoriaEntity.builder()
                .nome("CategoriaTeste")
                .build();

    }

    @Test
    @DisplayName("Teste para ver todos produtos listados")
    void deveListarTodosProdutos(){

        List<ProdutoEntity> produtos = List.of(produto1, produto2);
        when(produtoRepository.findAll()).thenReturn(produtos);


        //ACT

        List<ProdutoEntity> resultado = produtoService.listarProdutos();


        //ASSERT
        //assertIterableEquals(resultado, produtos);
        assertEquals(produtos,resultado);
    }

    @Test
    @DisplayName("Teste para ver se o produto foi criado com sucesso")
    void deveCriarProdutoComSucesso() throws NaoEncontradoException, JaExisteException {

    when(produtoRepository.findByNome(produtoDto.getNome())).thenReturn(Optional.empty());
    when(categoriaRepository.findById((produtoDto.getCategoriaId()))).thenReturn(Optional.of(categoria1));

    assertThrows(JaExisteException.class, () -> produtoService.criarProduto(produtoDto));

    verify(produtoRepository).save(any(ProdutoEntity.class));

    }

    @Test
    @DisplayName("Teste atualizar produto")
    void deveAtualizarProduto() throws NaoEncontradoException {

        when(produtoRepository.findById(produto1.getId())).thenReturn(Optional.of(produto1));

        produtoService.atualizarProduto(produto1.getId(), produtoDto);
        verify(produtoRepository).save(any(ProdutoEntity.class));

    }


    @Test
    @DisplayName("Get Produto")
    void deveRetornarProduto() throws NaoEncontradoException{

        when(produtoRepository.findById(produto1.getId())).thenReturn(Optional.of(produto1));
        produtoService.getProduto(produto1.getId());

        verify(produtoRepository).findById(produto1.getId());
    }

    @Test
    @DisplayName("Teste deletar produto")
    void deveDeletarProduto(){

        produtoService.deletarProduto(produto1.getId());

        verify(produtoRepository).deleteById(produto1.getId());

    }




}