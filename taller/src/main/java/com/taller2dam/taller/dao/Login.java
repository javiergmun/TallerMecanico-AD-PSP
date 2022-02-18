package com.taller2dam.taller.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "login")
@NamedQuery(name = "login.findAll", query = "SELECT l FROM Login l")
public class Login {

    private Long id;
    private Boolean activo;
    private String token;
    private Usuario usuario;

    @Id
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    @Basic
    @Column(name="esta_activo")
    public Boolean getActivo() {return activo;}
    public void setActivo(Boolean activo) {this.activo = activo;}

    @OneToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    @Basic
    @Column(name = "token")
    public String getToken() {return token;}
    public void setToken(String token) {this.token = token;}

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", activo=" + activo +
                ", token='" + token + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
