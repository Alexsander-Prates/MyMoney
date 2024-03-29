package br.com.alexpratesdev.backendmymoney.entity;

import br.com.alexpratesdev.backendmymoney.dto.GastoDTO;
import br.com.alexpratesdev.backendmymoney.dto.RegistroUsuarioDTO;
import br.com.alexpratesdev.backendmymoney.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
//esse criamos os registros no banco de dados
@Table(name = "USUARIO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity implements UserDetails {

    public UsuarioEntity(Long usuarioId) {

    }

    public UsuarioEntity(UsuarioDTO usuarioDTO, List<Long> gastoIds, List<Long> categoriaIds) {
        this.nome=usuarioDTO.getNome();
        this.documento = usuarioDTO.getDocumento();
        this.email = usuarioDTO.getEmail();
        this.senha = usuarioDTO.getSenha();
        this.login = usuarioDTO.getLogin();
        this.saldo = usuarioDTO.getSaldo();
        if (gastoIds != null && !gastoIds.isEmpty()) {
            this.gastoEntity = gastoIds.stream()
                    .map(GastoEntity::new)
                    .collect(Collectors.toList());
        }
        if (categoriaIds != null && !categoriaIds.isEmpty()) {
            this.categoriaEntity = categoriaIds.stream()
                    .map(CategoriaEntity::new)
                    .collect(Collectors.toList());
        }
    }

    public UsuarioEntity(UsuarioDTO usuarioDTO) {
        this.nome=usuarioDTO.getNome();
        this.documento = usuarioDTO.getDocumento();
        this.email = usuarioDTO.getEmail();
        this.senha = usuarioDTO.getSenha();
        this.login = usuarioDTO.getLogin();
        this.saldo = usuarioDTO.getSaldo();
    }

    public UsuarioEntity(RegistroUsuarioDTO registroUsuarioDTO, String senhaCriptografadaO) {
        this.nome=registroUsuarioDTO.getNome();
        this.documento = registroUsuarioDTO.getDocumento();
        this.email = registroUsuarioDTO.getEmail();
        this.senha = senhaCriptografadaO;
        this.login = registroUsuarioDTO.getLogin();
        this.saldo = registroUsuarioDTO.getSaldo();
    }


    public UsuarioEntity(Long usuarioId, Long gastoId) {
        this.id = usuarioId;
        this.gastoEntity.add(new GastoEntity(gastoId)); // Crie um novo gasto com o ID fornecido
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String documento;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, unique = true)
    private String login;

    @Column
    private BigDecimal saldo;


    @OneToMany(mappedBy = "usuarioEntity")
    private List<GastoEntity> gastoEntity;


    @OneToMany(mappedBy = "usuarioEntity")
    private List<CategoriaEntity> categoriaEntity;




    //metodo equals recebe hasCode para verificar se o ID do objeto será igual a uma nova instancia com o mesmo ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USUARIO"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
