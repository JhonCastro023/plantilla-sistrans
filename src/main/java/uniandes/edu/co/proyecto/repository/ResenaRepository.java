package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Resena;

import java.util.Collection;

public interface ResenaRepository extends JpaRepository<Resena, Long> {

    @Query(value = "SELECT * FROM resena", nativeQuery = true)
    Collection<Resena> darResenas();

    @Query(value = "SELECT * FROM resena WHERE id = :id", nativeQuery = true)
    Resena darResena(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO resena (id, id_viaje, comentario, calificacion) VALUES (alpescab_seq.nextval, :idViaje, :comentario, :calificacion)", nativeQuery = true)
    void insertarResena(@Param("idViaje") Long idViaje, @Param("comentario") String comentario, @Param("calificacion") int calificacion);

    @Modifying
    @Transactional
    @Query(value = "UPDATE resena SET comentario = :comentario, calificacion = :calificacion WHERE id = :id", nativeQuery = true)
    void actualizarResena(@Param("id") Long id, @Param("comentario") String comentario, @Param("calificacion") int calificacion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM resena WHERE id = :id", nativeQuery = true)
    void eliminarResena(@Param("id") Long id);
}
