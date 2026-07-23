package br.com.lucaslleonardo.estoque_spring.Controller;


import br.com.lucaslleonardo.estoque_spring.dto.EstoqueDto;
import br.com.lucaslleonardo.estoque_spring.exception.NaoEncontradoException;
import br.com.lucaslleonardo.estoque_spring.service.EstoqueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PutMapping("/entrada/{id}")
    public void entradaEstoque(
            @PathVariable Long id,
            @RequestBody @Valid EstoqueDto estoqueDto) throws NaoEncontradoException {

        estoqueService.entradaEstoque(id, estoqueDto);
    }

    @PutMapping("/saida/{id}")
    public void saidaEstoque(
            @PathVariable Long id,
            @RequestBody @Valid EstoqueDto estoqueDto) throws NaoEncontradoException {

        estoqueService.saidaEstoque(id, estoqueDto);
    }

}
