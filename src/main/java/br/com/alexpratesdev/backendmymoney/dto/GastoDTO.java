package br.com.alexpratesdev.backendmymoney.dto;


import br.com.alexpratesdev.backendmymoney.entity.CategoriaEntity;
import br.com.alexpratesdev.backendmymoney.entity.GastoEntity;
import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GastoDTO {

    public GastoDTO(){

    }

    public GastoDTO(GastoEntity gastoEntity){
        BeanUtils.copyProperties(gastoEntity, this);
        //construtor para converter entity para DTO
    }

    public GastoDTO(Long id, BigDecimal valor, String descricao, LocalDateTime dia, Long usuario_id, Long categoria_id) {
        this.id = id;
        this.valor = valor;
        this.descricao = descricao;
        this.dia = dia;
        this.usuario_id = usuario_id;
        this.categoria_id = categoria_id;
    }




    private Long id;

    @DecimalMin(value = "0.0", inclusive = false, message = "O consumo deve ser maior que zero")
    @DecimalMax(value = "9999999999.99", message = "O consumo não pode ser maior que 9999999999.99")
    private BigDecimal valor;

    @Size(min=4,max=50)
    private String descricao;

    private LocalDateTime dia;

    @Min(value = 1 , message = "Usuário obrigatório")
    private Long usuario_id;

    @Min(value = 1 , message = "Categoria obrigatório")
    private Long categoria_id;



}
