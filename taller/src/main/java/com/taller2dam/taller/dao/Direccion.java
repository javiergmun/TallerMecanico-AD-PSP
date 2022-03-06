package com.taller2dam.taller.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
/*
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "direccion")
@NamedQuery(name = "direccion.findAll", query = "SELECT d FROM Direccion d")
public class Direccion {

    private long id;
    private String calle;
    private String numero;
    private String codigo;
    private String localidad;


    @Id
    @GeneratedValue
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    @Basic
    @Column(name = "calle")
    public String getCalle() {return calle;}
    public void setCalle(String calle) {this.calle = calle;}

    @Basic
    @Column(name = "numero")
    public String getNumero() {return numero;}
    public void setNumero(String numero) {this.numero = numero;}

    @Basic
    @Column(name = "codigo")
    public String getCodigo() {return codigo;}
    public void setCodigo(String codigo) {this.codigo = codigo;}

    @Basic
    @Column(name = "localidad")
    public String getLocalidad() {return localidad;}
    public void setLocalidad(String localidad) {this.localidad = localidad;}

    @Override
    public String toString() {
        return "Direccion{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", codigo='" + codigo + '\'' +
                ", localidad='" + localidad + '\'' +
                '}';
    }
}

 */