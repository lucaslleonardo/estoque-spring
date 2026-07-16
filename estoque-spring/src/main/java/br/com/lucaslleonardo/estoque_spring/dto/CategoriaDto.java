package br.com.lucaslleonardo.estoque_spring.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CategoriaDto {

    @NotBlank
    private String nome;

}
