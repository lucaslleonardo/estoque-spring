package br.com.lucaslleonardo.estoque_spring.service;

import br.com.lucaslleonardo.estoque_spring.dto.EstoqueDto;
import br.com.lucaslleonardo.estoque_spring.entity.ProdutoEntity;
import br.com.lucaslleonardo.estoque_spring.exception.NaoEncontradoException;
import br.com.lucaslleonardo.estoque_spring.repository.IProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstoqueServiceTest {

    @InjectMocks
    private EstoqueService estoqueService;

    @Mock
    IProdutoRepository produtoRepository;

    private EstoqueDto estoqueDto;
    private ProdutoEntity produto;


    @BeforeEach
    void setUp() {
        produto = ProdutoEntity.builder()
                .id(1L)
                .nome("Produto")
                .quantidade(10)
                .build();

        estoqueDto = EstoqueDto.builder()
                .quantidade(5)
                .build();
    }

    @Test
    @DisplayName("Teste para verficar entrada no estoque")
    void deveEntrarNoEstoque() throws NaoEncontradoException {

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        estoqueService.entradaEstoque(produto.getId(), estoqueDto);

        assertEquals(15, produto.getQuantidade());

        verify(produtoRepository).save(produto);

    }

    @Test
    @DisplayName("Teste erro produto nao encontrado")
    void deveMostrarEntradaException() throws NaoEncontradoException {
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> estoqueService.entradaEstoque(produto.getId(), estoqueDto));
        verify(produtoRepository, never()).save(any(ProdutoEntity.class));
    }


    @Test
    @DisplayName("Teste para verificar saida no estoque")
    void deveSairNoEstoque() throws NaoEncontradoException {

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        estoqueService.saidaEstoque(produto.getId(), estoqueDto);

        assertEquals(5, produto.getQuantidade());
        verify(produtoRepository).save(produto);

    }

    @Test
    @DisplayName("Teste erro produto nao encontrado/saida")
    void deveMostrarSaidaException() throws NaoEncontradoException {
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> estoqueService.saidaEstoque(produto.getId(), estoqueDto));
        verify(produtoRepository, never()).save(any(ProdutoEntity.class));
    }



}