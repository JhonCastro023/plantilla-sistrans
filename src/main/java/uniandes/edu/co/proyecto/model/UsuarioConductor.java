package uniandes.edu.co.proyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_conductor")
public class UsuarioConductor {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alpescab_seq")
    @SequenceGenerator(name = "alpescab_seq", sequenceName = "alpescab_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "NUMERO_LICENCIA")
    private String numeroLicencia;

    @Column(name = "VEHICULO")
    private String vehiculo;

    @Column(name = "ACTIVO")
    private boolean activo;

    @Column(name = "COSTO")
    private double costo;

    public UsuarioConductor() {}

    public UsuarioConductor(String nombre, String numeroLicencia, String vehiculo, boolean activo) {
        this.nombre = nombre;
        this.numeroLicencia = numeroLicencia;
        this.vehiculo = vehiculo;
        this.activo = activo;
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

    public boolean getActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}

