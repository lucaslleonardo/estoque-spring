package br.com.lucaslleonardo.estoque_spring.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProdutoDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    private Double preco;

    @NotNull
    private Integer quantidade;

    @NotBlank
    private String categoria;

    private Long categoriaId;

}
