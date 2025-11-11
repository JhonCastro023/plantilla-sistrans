package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.SolicitudServicio;

import java.util.Collection;

public interface SolicitudServicioRepository extends JpaRepository<SolicitudServicio, Long> {

    @Query(value = "SELECT * FROM solicitud_servicio", nativeQuery = true)
    Collection<SolicitudServicio> darSolicitudesServicio();

    @Query(value = "SELECT * FROM solicitud_servicio WHERE id = :id", nativeQuery = true)
    SolicitudServicio darSolicitudServicio(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO solicitud_servicio (id, origen, destino, fecha) VALUES (alpescab_seq.nextval, :origen, :destino, :fecha)", nativeQuery = true)
    void insertarSolicitudServicio(@Param("origen") String origen, @Param("destino") String destino, @Param("fecha") String fecha);

    @Modifying
    @Transactional
    @Query(value = "UPDATE solicitud_servicio SET origen = :origen, destino = :destino, fecha = :fecha WHERE id = :id", nativeQuery = true)
    void actualizarSolicitudServicio(@Param("id") Long id, @Param("origen") String origen, @Param("destino") String destino, @Param("fecha") String fecha);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM solicitud_servicio WHERE id = :id", nativeQuery = true)
    void eliminarSolicitudServicio(@Param("id") Long id);
}
