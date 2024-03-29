package br.com.alexpratesdev.backendmymoney.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class LoginDTO {

    public LoginDTO(){

    }

    public LoginDTO(String usuarioEntity){
        BeanUtils.copyProperties(usuarioEntity, this);
    }

    public LoginDTO(String login, String senha, String token) {
        this.login = login;
        this.senha = senha;
        this.token = token;
    }

    @NotBlank(message = "login é obrigatório")
    @Size(min=6, max=20)
    @Pattern(regexp = "^[A-Z]+(.)*")
    private String login;

    @NotBlank(message = "senha é obrigatório")
    @Size(min=6, max=50)
    private String senha;

    private String token;


}
