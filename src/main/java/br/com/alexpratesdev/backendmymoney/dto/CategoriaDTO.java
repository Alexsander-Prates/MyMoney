package br.com.alexpratesdev.backendmymoney.dto;

import br.com.alexpratesdev.backendmymoney.entity.CategoriaEntity;
import br.com.alexpratesdev.backendmymoney.entity.GastoEntity;
import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;

import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class CategoriaDTO {

    public CategoriaDTO(){

    }

    public CategoriaDTO(CategoriaEntity categoriaEntity){
        BeanUtils.copyProperties(categoriaEntity, this);
        //construtor para converter entity para DTO

    }

    public CategoriaDTO(Long id, String nome, String descricao, Long usuario_id) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.usuario_id = usuario_id;
    }

    @Valid


    private Long id;

    @NotNull(message = "Nome Categoria é obrigatório")
    @NotBlank(message = "Nome Categoria é obrigatório")
    @Size(min=2, max=50)
    private String nome;

    @Size(min=4,max=50)
    private String descricao;

    @Min(value = 1 , message = "Usuário obrigatório")
    private Long usuario_id;

}
