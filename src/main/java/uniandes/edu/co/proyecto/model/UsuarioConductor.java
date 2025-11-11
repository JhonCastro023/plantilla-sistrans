package uniandes.edu.co.proyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_conductor")
public class UsuarioConductor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String numeroLicencia;
    private String vehiculo;

    public UsuarioConductor() {}

    public UsuarioConductor(String nombre, String numeroLicencia, String vehiculo) {
        this.nombre = nombre;
        this.numeroLicencia = numeroLicencia;
        this.vehiculo = vehiculo;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNumeroLicencia() { return numeroLicencia; }
    public void setNumeroLicencia(String numeroLicencia) { this.numeroLicencia = numeroLicencia; }

    public String getVehiculo() { return vehiculo; }
    public void setVehiculo(String vehiculo) { this.vehiculo = vehiculo; }
}
