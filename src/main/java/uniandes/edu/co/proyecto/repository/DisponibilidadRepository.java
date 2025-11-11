package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Disponibilidad;

import java.time.LocalDateTime;
import java.util.Collection;

public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Long> {

    @Query(value = "SELECT * FROM disponibilidad", nativeQuery = true)
    Collection<Disponibilidad> darDisponibilidades();

    @Query(value = "SELECT * FROM disponibilidad WHERE id = :id", nativeQuery = true)
    Disponibilidad darDisponibilidad(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO disponibilidad (id, id_conductor, fecha) VALUES (alpescab_seq.nextval, :idConductor, :fecha)", nativeQuery = true)
    void insertarDisponibilidad(@Param("idConductor") Long idConductor, @Param("fecha") LocalDateTime fecha);

    @Modifying
    @Transactional
    @Query(value = "UPDATE disponibilidad SET fecha = :fecha, WHERE id = :id", nativeQuery = true)
    void actualizarDisponibilidad(@Param("id") Long id, @Param("fecha") LocalDateTime fecha);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM disponibilidad WHERE id = :id", nativeQuery = true)
    void eliminarDisponibilidad(@Param("id") Long id);
}
