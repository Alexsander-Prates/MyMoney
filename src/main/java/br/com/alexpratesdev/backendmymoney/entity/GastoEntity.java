package br.com.alexpratesdev.backendmymoney.entity;




import br.com.alexpratesdev.backendmymoney.dto.GastoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
//esse criamos os registros no banco de dados
@Table(name = "GASTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GastoEntity {



    public GastoEntity(GastoDTO gastoDTO) {
        this.valor = gastoDTO.getValor();
        this.descricao = gastoDTO.getDescricao();
        this.dia = gastoDTO.getDia();
        this.usuarioEntity=new UsuarioEntity(gastoDTO.getUsuario_id());
        this.categoriaEntity=new CategoriaEntity(gastoDTO.getCategoria_id());
    }

    public GastoEntity(Long id) {
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal valor;

    @Column
    private String descricao;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dia;


    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuarioEntity;


    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
    private CategoriaEntity categoriaEntity;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GastoEntity that = (GastoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
