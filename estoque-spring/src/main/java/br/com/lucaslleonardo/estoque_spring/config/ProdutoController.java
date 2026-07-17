package br.com.lucaslleonardo.estoque_spring.config;


import br.com.lucaslleonardo.estoque_spring.dto.ProdutoDto;
import br.com.lucaslleonardo.estoque_spring.entity.ProdutoEntity;
import br.com.lucaslleonardo.estoque_spring.handler.NaoEncontradoException;
import br.com.lucaslleonardo.estoque_spring.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@Validated
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarProduto (@RequestBody @Valid ProdutoDto produtoDto) throws NaoEncontradoException {
        produtoService.criarProduto(produtoDto);
    }

    @GetMapping("/lista")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoEntity> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntity> getProduto(@PathVariable Long id) throws NaoEncontradoException {
        return ResponseEntity.ok(produtoService.getProduto(id));
    }

    @PutMapping("/{id}/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoDto) throws NaoEncontradoException {
        produtoService.atualizarProduto(id, produtoDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Long id) throws NaoEncontradoException {
        produtoService.deletarProduto(id);
    }


}
