package uniandes.edu.co.proyecto.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitud_servicio")
public class SolicitudServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String origen;
    private String destino;
    private LocalDateTime fechaSolicitud;

    @ManyToOne
    private UsuarioCliente cliente;

    public SolicitudServicio() {}

    public SolicitudServicio(String origen, String destino, LocalDateTime fechaSolicitud, UsuarioCliente cliente) {
        this.origen = origen;
        this.destino = destino;
        this.fechaSolicitud = fechaSolicitud;
        this.cliente = cliente;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public UsuarioCliente getCliente() { return cliente; }
    public void setCliente(UsuarioCliente cliente) { this.cliente = cliente; }
}
