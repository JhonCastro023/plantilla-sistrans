package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

import uniandes.edu.co.proyecto.model.SolicitudServicio;

import java.time.LocalDateTime;
import java.util.Collection;

public interface SolicitudServicioRepository extends JpaRepository<SolicitudServicio, Long> {

    @Query(value = "SELECT * FROM solicitud_servicio", nativeQuery = true)
    Collection<SolicitudServicio> darSolicitudesServicio();

    @Query(value = "SELECT * FROM solicitud_servicio WHERE id = :id", nativeQuery = true)
    SolicitudServicio darSolicitudServicio(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO solicitud_servicio (id, id_usuario, tipo, nivel, solicitado_en, distancia_km, tarifa_calculada, estado, id_medio_pago, id_tarifa) VALUES (solicitud_servicio_seq.NEXTVAL, :idUsuario, :tipo, :nivel, :solicitadoEn, :distanciaKm, :tarifaCalculada, :estado, :idMedioPago, :idTarifa)", nativeQuery = true)
    void insertarSolicitudServicio(
        @Param("idUsuario") Long idUsuario,
        @Param("tipo") String tipo,
        @Param("nivel") String nivel,
        @Param("solicitadoEn") LocalDateTime solicitadoEn,
        @Param("distanciaKm") Double distanciaKm,
        @Param("tarifaCalculada") Double tarifaCalculada,
        @Param("estado") String estado,
        @Param("idMedioPago") Long idMedioPago,
        @Param("idTarifa") Long idTarifa
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE solicitud_servicio SET origen = :origen, destino = :destino, fecha = :fecha WHERE id = :id", nativeQuery = true)
    void actualizarSolicitudServicio(@Param("id") Long id, @Param("origen") String origen, @Param("destino") String destino, @Param("fecha") String fecha);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM solicitud_servicio WHERE id = :id", nativeQuery = true)
    void eliminarSolicitudServicio(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = """
    UPDATE solicitud_servicio
    SET estado = 'finalizado',
        finalizado_en = :finalizadoEn,
        distancia_final = :distanciaFinal,
        tarifa_final = :tarifaFinal
    WHERE id = :id
      AND estado = 'en_curso'
""", nativeQuery = true)
int finalizarServicio(
        @Param("id") Long id,
        @Param("distanciaFinal") Double distanciaFinal,
        @Param("tarifaFinal") Double tarifaFinal,
        @Param("finalizadoEn") LocalDateTime finalizadoEn
);

        @Modifying
        @Transactional
        @Query(value = "UPDATE solicitud_servicio " +
                "SET estado = 'finalizado', " +
                "    finalizado_en = :finalizadoEn, " +
                "    distancia_final = :distanciaFinal, " +
                "    tarifa_final = :tarifaFinal " +
                "WHERE id = :id " +
                "  AND estado = 'en_curso'", nativeQuery = true)
        int finalizarSolicitud(
                @Param("id") Long id,
                @Param("finalizadoEn") LocalDateTime finalizadoEn,
                @Param("distanciaFinal") Double distanciaFinal,
                @Param("tarifaFinal") Double tarifaFinal
        );


}
