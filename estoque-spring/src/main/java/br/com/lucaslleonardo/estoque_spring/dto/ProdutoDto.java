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

    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @NotBlank(message = "Campo obrigatório")
    private String descricao;

    @NotNull(message = "Campo obrigatório")
    private Double preco;

    @NotNull(message = "Campo obrigatório")
    private Integer quantidade;

    @NotBlank(message = "Campo obrigatório")
    private String categoria;

    private Long categoriaId;

}
