package br.com.alexpratesdev.backendmymoney.entity;

import br.com.alexpratesdev.backendmymoney.dto.CategoriaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Data
@Entity
@Table(name = "CATEGORIA")
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaEntity {

    public CategoriaEntity(CategoriaDTO categoriaDTO){
        this.nome = categoriaDTO.getNome();
        this.descricao = categoriaDTO.getDescricao();;
        //construtor para converter entity para DTO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    @NotNull
    @NotBlank(message = "Não pode ser vazio")
    @Size(min=3,max=50)
    private String nome;

    @Column
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuarioEntity;

    public CategoriaEntity(Long id) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaEntity that = (CategoriaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
