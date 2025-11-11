package uniandes.edu.co.proyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "resena")
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer calificacion;
    private String comentario;

    @ManyToOne
    private Viaje viaje;

    public Resena() {}

    public Resena(Integer calificacion, String comentario, Viaje viaje) {
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.viaje = viaje;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getCalificacion() { return calificacion; }
    public void setCalificacion(Integer calificacion) { this.calificacion = calificacion; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public Viaje getViaje() { return viaje; }
    public void setViaje(Viaje viaje) { this.viaje = viaje; }
}
