package br.com.alexpratesdev.backendmymoney.dto;

import br.com.alexpratesdev.backendmymoney.entity.CategoriaEntity;
import br.com.alexpratesdev.backendmymoney.entity.GastoEntity;
import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;


import java.math.BigDecimal;
import java.util.List;


@Data
//esse apresentamos os dados no front-end
public class UsuarioDTO {



    public UsuarioDTO(){

    }

    public UsuarioDTO (UsuarioEntity usuarioEntity){
        BeanUtils.copyProperties(usuarioEntity, this);
        //construtor para converter entity para DTO
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


    @NotNull(message = "senha é obrigatório")
    @NotBlank(message = "senha é obrigatório")
    @Size(min=8, max=16)
    private String senha;

    @Size(max=11)
    @NotNull(message = "login é obrigatório")
    @NotBlank(message = "login é obrigatório")
    @Size(max=11)
    private String login;

    @DecimalMin(value = "0.0", inclusive = false, message = "O saldo deve ser maior que zero")
    @DecimalMax(value = "9999999999.99", message = "O saldo não pode ser maior que 9999999999.99")
    private BigDecimal saldo;

    private Long gasto_id;

    private Long categoria_id;

}
