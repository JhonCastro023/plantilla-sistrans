package uniandes.edu.co.proyecto.model;
import jakarta.persistence.*;

@Entity
@Table(name = "tarifa")
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorBase;
    private Double costoPorKm;

    public Tarifa() {}

    public Tarifa(Double valorBase, Double costoPorKm) {
        this.valorBase = valorBase;
        this.costoPorKm = costoPorKm;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getValorBase() { return valorBase; }
    public void setValorBase(Double valorBase) { this.valorBase = valorBase; }

    public Double getCostoPorKm() { return costoPorKm; }
    public void setCostoPorKm(Double costoPorKm) { this.costoPorKm = costoPorKm; }
}

