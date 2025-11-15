package uniandes.edu.co.proyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name="USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String email;

    private String celular;

    private String cedula;

    public Usuario() {}

    public Usuario(String nombre, String email, String celular, String cedula) {
        this.nombre = nombre;
        this.email = email;
        this.celular = celular;
        this.cedula = cedula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }
    public String getCedula() {
        return cedula;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return this.nombre + " | " + this.email + " | " + this.celular + " | " + this.cedula;
    }
}
