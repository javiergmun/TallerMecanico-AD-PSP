package com.taller2dam.taller.dao.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
//@EntityListeners(AuditingEntityListener.class) //Con esto podríamos obtener la fecha  y hora de creación del usuario al usar el @CreateDate sobre un LocalDateTime
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UsuarioEntity implements UserDetails {

    @Id
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    //@CreatedDate
    //private LocalDateTime createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(rol-> new SimpleGrantedAuthority("ROLE_"+ rol.name())).collect(Collectors.toList());
    }



    //A estos dos métodos no hace falta que le demos implementación si hemos usado lombok y hemos llamado igual al os campos

    //@Override
    //public String getPassword() {
    //    return null;
    //}

    //@Override
    //public String getUsername() {
     //   return null;
    //}


    //Estos métodos serían para gestionar el bloqueo de cuentas. Si no los vamos a gestionar, les daremos true por defecto

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
