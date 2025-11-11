package uniandes.edu.co.proyecto.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "disponibilidad")
public class Disponibilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime inicio;
    private LocalDateTime fin;

    @ManyToOne
    private UsuarioConductor conductor;

    public Disponibilidad() {}

    public Disponibilidad(LocalDateTime inicio, LocalDateTime fin, UsuarioConductor conductor) {
        this.inicio = inicio;
        this.fin = fin;
        this.conductor = conductor;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getInicio() { return inicio; }
    public void setInicio(LocalDateTime inicio) { this.inicio = inicio; }

    public LocalDateTime getFin() { return fin; }
    public void setFin(LocalDateTime fin) { this.fin = fin; }

    public UsuarioConductor getConductor() { return conductor; }
    public void setConductor(UsuarioConductor conductor) { this.conductor = conductor; }
}
