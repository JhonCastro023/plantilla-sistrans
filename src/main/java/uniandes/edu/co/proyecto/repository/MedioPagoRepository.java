package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.MedioPago;

import java.util.Collection;

public interface MedioPagoRepository extends JpaRepository<MedioPago, Long> {

    @Query(value = "SELECT * FROM medio_pago", nativeQuery = true)
    Collection<MedioPago> darMediosPago();

    @Query(value = "SELECT * FROM medio_pago WHERE id = :id", nativeQuery = true)
    MedioPago darMedioPago(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO medio_pago (id, tipo, descripcion) VALUES (alpescab_seq.nextval, :tipo, :descripcion)", nativeQuery = true)
    void insertarMedioPago(@Param("tipo") String tipo, @Param("descripcion") String descripcion);

    @Modifying
    @Transactional
    @Query(value = "UPDATE medio_pago SET tipo = :tipo, descripcion = :descripcion WHERE id = :id", nativeQuery = true)
    void actualizarMedioPago(@Param("id") Long id, @Param("tipo") String tipo, @Param("descripcion") String descripcion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM medio_pago WHERE id = :id", nativeQuery = true)
    void eliminarMedioPago(@Param("id") Long id);
}
