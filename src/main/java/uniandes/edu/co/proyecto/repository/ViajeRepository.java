package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Viaje;

import java.util.Collection;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query(value = "SELECT * FROM viaje", nativeQuery = true)
    Collection<Viaje> darViajes();

    @Query(value = "SELECT * FROM viaje WHERE id = :id", nativeQuery = true)
    Viaje darViaje(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO viaje (id, id_cliente, id_conductor, fecha, estado) VALUES (alpescab_seq.nextval, :idCliente, :idConductor, :fecha, :estado)", nativeQuery = true)
    void insertarViaje(@Param("idCliente") Long idCliente, @Param("idConductor") Long idConductor, @Param("fecha") String fecha, @Param("estado") String estado);

    @Modifying
    @Transactional
    @Query(value = "UPDATE viaje SET estado = :estado WHERE id = :id", nativeQuery = true)
    void actualizarViaje(@Param("id") Long id, @Param("estado") String estado);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM viaje WHERE id = :id", nativeQuery = true)
    void eliminarViaje(@Param("id") Long id);
}
