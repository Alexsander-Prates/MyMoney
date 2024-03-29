package br.com.alexpratesdev.backendmymoney.dto;

import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Data
public class RegistroUsuarioDTO {



    public RegistroUsuarioDTO(){

    }

    public RegistroUsuarioDTO(UsuarioEntity usuarioEntity){
        BeanUtils.copyProperties(usuarioEntity, this);
    }



    private Long id;


    @NotBlank(message = "Nome é obrigatório")
    @Size(min=2, max=50)
    @Pattern(regexp = "^[A-Z]+(.)*")
    private String nome;

    @NotNull(message = "documento é obrigatório")
    @NotBlank(message = "documento é obrigatório")
    @Size(max=11)
    @CPF
    private String documento;

    @NotNull(message = "email é obrigatório")
    @NotBlank(message = "email é obrigatório")
    @Email
    private String email;

    @NotBlank(message = "login é obrigatório")
    @Size(min=6, max=20)
    @Pattern(regexp = "^[A-Z]+(.)*")
    private String login;

    @NotBlank(message = "senha é obrigatório")
    @Size(min=6, max=50)
    private String senha;


    @DecimalMin(value = "0.0", inclusive = false, message = "O saldo deve ser maior que zero")
    @DecimalMax(value = "9999999999.99", message = "O saldo não pode ser maior que 9999999999.99")
    private BigDecimal saldo;

    private Long gasto_id;

    private Long categoria_id;
}
