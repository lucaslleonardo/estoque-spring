package br.com.lucaslleonardo.estoque_spring.service;

import br.com.lucaslleonardo.estoque_spring.dto.CategoriaDto;
import br.com.lucaslleonardo.estoque_spring.entity.CategoriaEntity;
import br.com.lucaslleonardo.estoque_spring.entity.ProdutoEntity;
import br.com.lucaslleonardo.estoque_spring.exception.JaExisteException;
import br.com.lucaslleonardo.estoque_spring.exception.NaoEncontradoException;
import br.com.lucaslleonardo.estoque_spring.repository.ICategoriaRepository;
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
class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private ICategoriaRepository categoriaRepository;

    private CategoriaEntity categoria;

    private CategoriaDto categoriaDto;


    @BeforeEach
    void setUp() {

        categoria = CategoriaEntity.builder()
                .id(1L)
                .nome("ProdutoLimpeza")
                .build();

        categoriaDto = CategoriaDto.builder()
                .nome("ProdutoLimpeza")
                .build();
    }

    @Test
    @DisplayName("Teste para ver se vai chamar exception no criar categoria")
    void deveDarErroCriarCategoriaNova() throws JaExisteException {

        when(categoriaRepository.findByNome(categoriaDto.getNome())).thenReturn(Optional.of(categoria));

        assertThrows(JaExisteException.class, () -> categoriaService.criarCategoria(categoriaDto));

    }

    @Test
    @DisplayName("Teste para salvar categoria nova")
    void deveSalvarCategoriaNova() {
        when(categoriaRepository.findByNome(categoriaDto.getNome())).thenReturn(Optional.empty());
        categoriaService.criarCategoria(categoriaDto);
        verify(categoriaRepository).save(any(CategoriaEntity.class));
    }

    @Test
    @DisplayName("Teste editar categoria")
    void deveEditarCategoria() throws NaoEncontradoException {
        when(categoriaRepository.findById(categoria.getId())).thenReturn(Optional.of(categoria));
        categoriaService.editarCategoria(categoria.getId(), categoriaDto);
        verify(categoriaRepository).save(any(CategoriaEntity.class));
    }

    @Test
    @DisplayName("Teste pegar categoria")
    void devePegarCategoria() throws NaoEncontradoException {
        when(categoriaRepository.findById(categoria.getId())).thenReturn(Optional.of(categoria));
        categoriaService.getCategoria(categoria.getId());
        verify(categoriaRepository).findById(categoria.getId());
    }

    @Test
    @DisplayName("Teste listar todas categorias")
    void deveListarTodasCategorias(){
        List<CategoriaEntity> categorias = List.of(categoria);
        when(categoriaRepository.findAll()).thenReturn(categorias);

        List<CategoriaEntity> resultado = categoriaService.listarTodasCategorias();

        assertEquals(categorias, resultado);
    }

    @Test
    @DisplayName("Teste deletar categoria")
    void deveDeletarCategoria(){
        categoriaService.deletarCategoria(categoria.getId());
        verify(categoriaRepository).deleteById(categoria.getId());
    }
}