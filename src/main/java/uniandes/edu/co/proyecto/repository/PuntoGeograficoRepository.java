package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.PuntoGeografico;

import java.util.Collection;

public interface PuntoGeograficoRepository extends JpaRepository<PuntoGeografico, Long> {

    @Query(value = "SELECT * FROM punto_geografico", nativeQuery = true)
    Collection<PuntoGeografico> darPuntosGeograficos();

    @Query(value = "SELECT * FROM punto_geografico WHERE id = :id", nativeQuery = true)
    PuntoGeografico darPuntoGeografico(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO punto_geografico (id, nombre, direccion, latitud, longitud, ciudad_id) VALUES (alpescab_seq.nextval, :nombre, :direccion, :latitud, :longitud, :ciudadId)", nativeQuery = true)
    void insertarPuntoGeografico(@Param("nombre") String nombre,
                                 @Param("direccion") String direccion,
                                 @Param("latitud") double latitud,
                                 @Param("longitud") double longitud,
                                 @Param("ciudadId") Long ciudadId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE punto_geografico SET nombre = :nombre, direccion = :direccion, latitud = :latitud, longitud = :longitud, ciudad_id = :ciudadId WHERE id = :id", nativeQuery = true)
    void actualizarPuntoGeografico(@Param("id") Long id,
                                    @Param("nombre") String nombre,
                                    @Param("direccion") String direccion,
                                    @Param("latitud") double latitud,
                                    @Param("longitud") double longitud,
                                    @Param("ciudadId") Long ciudadId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM punto_geografico WHERE id = :id", nativeQuery = true)
    void eliminarPuntoGeografico(@Param("id") Long id);
}
