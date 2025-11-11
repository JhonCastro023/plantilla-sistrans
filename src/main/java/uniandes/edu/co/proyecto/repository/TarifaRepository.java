package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Tarifa;

import java.util.Collection;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

    @Query(value = "SELECT * FROM tarifa", nativeQuery = true)
    Collection<Tarifa> darTarifas();

    @Query(value = "SELECT * FROM tarifa WHERE id = :id", nativeQuery = true)
    Tarifa darTarifa(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tarifa (id, valor_base, valor_km) VALUES (alpescab_seq.nextval, :valorBase, :valorKm)", nativeQuery = true)
    void insertarTarifa(@Param("valorBase") Double valorBase, @Param("valorKm") Double valorKm);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tarifa SET valor_base = :valorBase, valor_km = :valorKm WHERE id = :id", nativeQuery = true)
    void actualizarTarifa(@Param("id") Long id, @Param("valorBase") Double valorBase, @Param("valorKm") Double valorKm);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tarifa WHERE id = :id", nativeQuery = true)
    void eliminarTarifa(@Param("id") Long id);
}
