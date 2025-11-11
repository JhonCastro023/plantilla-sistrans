package uniandes.edu.co.proyecto.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "viaje")
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Double costo;

    @ManyToOne
    private UsuarioConductor conductor;

    @ManyToOne
    private UsuarioCliente cliente;

    public Viaje() {}

    public Viaje(LocalDateTime fechaInicio, LocalDateTime fechaFin, Double costo,
                 UsuarioConductor conductor, UsuarioCliente cliente) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
        this.conductor = conductor;
        this.cliente = cliente;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDateTime getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }

    public Double getCosto() { return costo; }
    public void setCosto(Double costo) { this.costo = costo; }

    public UsuarioConductor getConductor() { return conductor; }
    public void setConductor(UsuarioConductor conductor) { this.conductor = conductor; }

    public UsuarioCliente getCliente() { return cliente; }
    public void setCliente(UsuarioCliente cliente) { this.cliente = cliente; }
}
