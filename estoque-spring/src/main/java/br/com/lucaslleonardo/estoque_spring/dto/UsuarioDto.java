package br.com.lucaslleonardo.estoque_spring.dto;


import br.com.lucaslleonardo.estoque_spring.roles.Cargos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UsuarioDto {

    @NotBlank
    private String usuario;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @NotNull
    private Cargos cargos;

}
