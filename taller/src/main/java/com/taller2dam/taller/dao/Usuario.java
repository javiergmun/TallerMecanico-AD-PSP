package com.taller2dam.taller.dao;

import com.taller2dam.taller.dao.Direccion;
import com.taller2dam.taller.dao.Login;
import com.taller2dam.taller.dao.Vehiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.util.Set;



@Entity
@EntityListeners(AuditingEntityListener.class) //Con esto podríamos obtener la fecha  y hora de creación del usuario al usar el @CreateDate sobre un LocalDateTime
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "usuario")
@NamedQuery(name = "usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario {

    private long id;
    private String dni;
    private String nombre;
    private Boolean administrador;
    private String telefono;
    private Direccion direccion;
    private String correo;
    private String password;    //Cifrarla o hacer que no se muestre
    private String imagen;
    private String bitmap; //Para la imagen de android
    private Set<Vehiculo> vehiculos;
    private Login login;

    @Id
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    @Basic
    @Column(name = "dni")
    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}

    @Basic
    @Column(name = "nombre")
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    @Basic
    @Column(name = "es_administrador")
    public Boolean getAdministrador() {return administrador;}
    public void setAdministrador(Boolean administrador) {this.administrador = administrador;}

    @Basic
    @Column(name = "telefono")
    public String getTelefono() {return telefono;}
    public void setTelefono(String phone) {this.telefono = phone;}

    @OneToOne
    @JoinColumn(name = "direccion", referencedColumnName = "id")
    public Direccion getDireccion() {return direccion;}
    public void setDireccion(Direccion address) {this.direccion = address;}

    @Basic
    @Column(name = "correo")
    public String getCorreo() {return correo;}
    public void setCorreo(String email) {this.correo = email;}

    @Basic
    @Column(name = "contraseña")
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    @Basic
    @Column(name = "imagen")
    public String getImagen() {return imagen;}
    public void setImagen(String imagen) {this.imagen = imagen;}

    @Basic
    @Column(name = "bitmap")
    public String getBitmap() {return bitmap;}
    public void setBitmap(String bitmap) {this.bitmap = bitmap;}

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "propietario", cascade = CascadeType.REMOVE)    //Ver Tipo de Cascada
    @OneToMany
    public Set<Vehiculo> getVehiculos() {return vehiculos;}
    public void setVehiculos(Set<Vehiculo> vehiculos) {this.vehiculos = vehiculos;}

    @OneToOne
    @JoinColumn(name = "login", referencedColumnName = "id")
    public Login getLogin() {return login;}
    public void setLogin(Login login) {this.login = login;}


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", administrador=" + administrador +
                ", telefono='" + telefono + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", imagen='" + imagen + '\'' +
                ", bitmap='" + bitmap + '\'' +
                ", vehiculos=" + vehiculos +
                ", login=" + login +
                '}';
    }
}