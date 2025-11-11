package uniandes.edu.co.proyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name="PUNTO_GEOGRAFICO")
public class PuntoGeografico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    private String direccion;

    private double latitud;

    private double longitud;

    @Column(name="ciudad_id")
    private Long ciudadId;

    public PuntoGeografico(){;}

    public PuntoGeografico(String nombre, String direccion, double latitud, double longitud, Long ciudadId) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.ciudadId = ciudadId;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Long getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Long ciudadId) {
        this.ciudadId = ciudadId;
    }

    @Override
    public String toString() {
        return this.nombre + " | " + this.direccion + " (" + this.latitud + ", " + this.longitud + ")";
    }
}
