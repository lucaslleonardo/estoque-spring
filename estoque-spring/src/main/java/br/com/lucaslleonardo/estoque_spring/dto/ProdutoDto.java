package br.com.lucaslleonardo.estoque_spring.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProdutoDto {

    private String nome;

    private String descricao;

    private Double preco;

    private Integer quantidade;

    private String categoria;

}
