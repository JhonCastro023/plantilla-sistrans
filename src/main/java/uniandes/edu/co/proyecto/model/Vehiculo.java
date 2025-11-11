package uniandes.edu.co.proyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name="VEHICULO")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String placa;

    private String marca;

    private String modelo;

    private int capacidad;

    @Column(name="conductor_id")
    private Long conductorId;

    public Vehiculo(){;}

    public Vehiculo(String placa, String marca, String modelo, int capacidad, Long conductorId) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.conductorId = conductorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Long getConductorId() {
        return conductorId;
    }

    public void setConductorId(Long conductorId) {
        this.conductorId = conductorId;
    }

    @Override
    public String toString() {
        return this.placa + " | " + this.marca + " " + this.modelo + " | Capacidad: " + this.capacidad;
    }
}
