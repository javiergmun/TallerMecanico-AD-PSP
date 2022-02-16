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

    private long id;
    private String correo;
    private String password;    //Cifrarla o hacer que no se muestre
    private String token;

    @Id
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    @Basic
    @Column(name = "correo")
    public String getCorreo() {return correo;}
    public void setCorreo(String email) {this.correo = email;}

    @Basic
    @Column(name = "contraseña")
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    @Basic
    @Column(name = "token")
    public String getToken() {return token;}
    public void setToken(String token) {this.token = token;}

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
