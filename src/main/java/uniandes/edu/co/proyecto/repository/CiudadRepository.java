package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Ciudad;

import java.util.Collection;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    @Query(value = "SELECT * FROM ciudad", nativeQuery = true)
    Collection<Ciudad> darCiudades();

    @Query(value = "SELECT * FROM ciudad WHERE id = :id", nativeQuery = true)
    Ciudad darCiudad(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ciudad (id, nombre) VALUES (alpescab_seq.nextval, :nombre)", nativeQuery = true)
    void insertarCiudad(@Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ciudad SET nombre = :nombre WHERE id = :id", nativeQuery = true)
    void actualizarCiudad(@Param("id") Long id, @Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ciudad WHERE id = :id", nativeQuery = true)
    void eliminarCiudad(@Param("id") Long id);
}
