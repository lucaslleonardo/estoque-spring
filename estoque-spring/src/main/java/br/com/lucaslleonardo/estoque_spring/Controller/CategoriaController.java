package br.com.lucaslleonardo.estoque_spring.Controller;


import br.com.lucaslleonardo.estoque_spring.dto.CategoriaDto;
import br.com.lucaslleonardo.estoque_spring.entity.CategoriaEntity;
import br.com.lucaslleonardo.estoque_spring.exception.NaoEncontradoException;
import br.com.lucaslleonardo.estoque_spring.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
@Validated
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarCategoria(@Valid @RequestBody CategoriaDto categoriaDto) throws NaoEncontradoException {
        categoriaService.criarCategoria(categoriaDto);
    }

    @PutMapping("/{id}/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public void editarCategoria(@Valid @RequestBody CategoriaDto categoriaDto, @PathVariable Long id) throws NaoEncontradoException {
        categoriaService.editarCategoria(id, categoriaDto);
    }

    @GetMapping("/lista")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoriaEntity> listarCategorias() {
        return categoriaService.listarTodasCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEntity> buscarCategoria(@PathVariable Long id) throws NaoEncontradoException {
        return ResponseEntity.ok(categoriaService.getCategoria(id));
    }

    @DeleteMapping("/{id}/deletar")
    public void deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
    }


}
