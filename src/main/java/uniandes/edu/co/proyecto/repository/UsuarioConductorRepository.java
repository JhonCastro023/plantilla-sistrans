package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.UsuarioConductor;

import java.util.Collection;

public interface UsuarioConductorRepository extends JpaRepository<UsuarioConductor, Long> {

    @Query(value = "SELECT * FROM usuario_conductor", nativeQuery = true)
    Collection<UsuarioConductor> darUsuariosConductores();

    @Query(value = "SELECT * FROM usuario_conductor WHERE id = :id", nativeQuery = true)
    UsuarioConductor darUsuarioConductor(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO usuario_conductor
            (id, nombre, numero_licencia, vehiculo, activo)
            VALUES (alpescab_seq.nextval, :nombre, :numeroLicencia, :vehiculo, 'S')
            """, nativeQuery = true)
    void insertarUsuarioConductor(
            @Param("nombre") String nombre,
            @Param("numeroLicencia") String numeroLicencia,
            @Param("vehiculo") String vehiculo
    );


    @Modifying
    @Transactional
    @Query(value = "UPDATE usuario_conductor SET nombre = :nombre, numero_licencia = :numeroLicencia, vehiculo = :vehiculo WHERE id = :id", nativeQuery = true)
    void actualizarUsuarioConductor(
        @Param("id") Long id,
        @Param("nombre") String nombre,
        @Param("numeroLicencia") String numeroLicencia,
        @Param("vehiculo") String vehiculo
    );


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuario_conductor WHERE id = :id", nativeQuery = true)
    void eliminarUsuarioConductor(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE usuario_conductor SET vehiculo = :vehiculo WHERE id = :id",
        nativeQuery = true
    )
    void registrarVehiculo(
        @Param("id") Long id,
        @Param("vehiculo") String vehiculo
);
}
