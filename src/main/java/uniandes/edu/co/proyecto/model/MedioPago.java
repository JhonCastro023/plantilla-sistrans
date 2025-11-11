package uniandes.edu.co.proyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "medio_pago")
public class MedioPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // Efectivo, Tarjeta, Nequi, etc.
    private String detalles;

    @ManyToOne
    private UsuarioCliente cliente;

    public MedioPago() {}

    public MedioPago(String tipo, String detalles, UsuarioCliente cliente) {
        this.tipo = tipo;
        this.detalles = detalles;
        this.cliente = cliente;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDetalles() { return detalles; }
    public void setDetalles(String detalles) { this.detalles = detalles; }

    public UsuarioCliente getCliente() { return cliente; }
    public void setCliente(UsuarioCliente cliente) { this.cliente = cliente; }
}
