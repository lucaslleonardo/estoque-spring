package br.com.lucaslleonardo.estoque_spring.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstoqueDto {

    @NotNull(message = "Campo obrigatório")
    @Min(1)
    private Integer quantidade;

}
