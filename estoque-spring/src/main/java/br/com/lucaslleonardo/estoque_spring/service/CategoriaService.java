package br.com.lucaslleonardo.estoque_spring.service;

import br.com.lucaslleonardo.estoque_spring.dto.CategoriaDto;
import br.com.lucaslleonardo.estoque_spring.entity.CategoriaEntity;
import br.com.lucaslleonardo.estoque_spring.handler.NaoEncontradoException;
import br.com.lucaslleonardo.estoque_spring.repository.ICategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private ICategoriaRepository categoriaRepository;

    public void criarCategoria (CategoriaDto categoriaDto) throws NaoEncontradoException {

        CategoriaEntity categorias = categoriaRepository.findByNome(categoriaDto.getNome())
                .orElse(null);

        if(categorias != null){
            throw new RuntimeException("Categoria já regitrada com esse nome.");
        }

        CategoriaEntity criarCategoraia = CategoriaEntity.builder()
                .nome(categoriaDto.getNome())
                .build();

        categoriaRepository.save(criarCategoraia);
    }

    public void editarCategoria (Long id ,CategoriaDto categoriaDto) throws NaoEncontradoException {

        CategoriaEntity categorias = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria nao encontrada no sistema"));

                categorias.setNome(categoriaDto.getNome());
                categoriaRepository.save(categorias);
    }

    public void deletarCategoria (Long id) throws NaoEncontradoException {
        categoriaRepository.deleteById(id);
    }

    public List<CategoriaEntity> listarTodasCategorias() {
        return categoriaRepository.findAll();
    }

//    public Optional<CategoriaEntity> getCategoria (Long id) throws NaoEncontradoException {
//        return categoriaRepository.findById(id);
//    }    Da pra usar tbm mas tem outro jeito


    public CategoriaEntity getCategoria(Long id) throws NaoEncontradoException {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria nao encontrada no sistema"));
    }


}
