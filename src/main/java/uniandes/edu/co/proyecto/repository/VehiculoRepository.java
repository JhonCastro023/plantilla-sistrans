package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Vehiculo;

import java.util.Collection;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    @Query(value = "SELECT * FROM vehiculo", nativeQuery = true)
    Collection<Vehiculo> darVehiculos();

    @Query(value = "SELECT * FROM vehiculo WHERE id = :id", nativeQuery = true)
    Vehiculo darVehiculo(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO vehiculo (id, placa, marca, modelo, capacidad, conductor_id) VALUES (alpescab_seq.nextval, :placa, :marca, :modelo, :capacidad, :conductorId)", nativeQuery = true)
    void insertarVehiculo(@Param("placa") String placa, @Param("marca") String marca,
                          @Param("modelo") String modelo, @Param("capacidad") int capacidad,
                          @Param("conductorId") Long conductorId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE vehiculo SET placa = :placa, marca = :marca, modelo = :modelo, capacidad = :capacidad, conductor_id = :conductorId WHERE id = :id", nativeQuery = true)
    void actualizarVehiculo(@Param("id") Long id, @Param("placa") String placa, @Param("marca") String marca,
                            @Param("modelo") String modelo, @Param("capacidad") int capacidad,
                            @Param("conductorId") Long conductorId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM vehiculo WHERE id = :id", nativeQuery = true)
    void eliminarVehiculo(@Param("id") Long id);
}
