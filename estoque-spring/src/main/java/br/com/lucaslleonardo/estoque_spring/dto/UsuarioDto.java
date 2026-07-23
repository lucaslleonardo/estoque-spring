package br.com.lucaslleonardo.estoque_spring.dto;


import br.com.lucaslleonardo.estoque_spring.roles.Cargos;
import jakarta.validation.constraints.Email;
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

    @NotBlank(message = "Campo obrigatório")
    private String usuario;

    @NotBlank
    @Email(message = "Campo obrigatório")
    private String email;

    @NotBlank(message = "Campo obrigatório")
    private String senha;

    @NotNull(message = "Campo obrigatório")
    private Cargos cargos;

}
