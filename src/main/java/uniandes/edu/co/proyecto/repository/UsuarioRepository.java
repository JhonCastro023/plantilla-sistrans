package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Usuario;

import java.util.Collection;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM usuario", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    @Query(value = "SELECT * FROM usuario WHERE id = :id", nativeQuery = true)
    Usuario darUsuario(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuario (id, nombre, email, celular, cedula) " +
                    "VALUES (alpescab_seq.nextval, :nombre, :email, :celular, :cedula)", 
           nativeQuery = true)
    void insertarUsuario(
        @Param("nombre") String nombre,
        @Param("email") String email,
        @Param("celular") String celular,
        @Param("cedula") String cedula
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuario SET nombre = :nombre, email = :email, celular = :celular, cedula = :cedula " +
                   "WHERE id = :id", 
           nativeQuery = true)
    void actualizarUsuario(
        @Param("id") Long id,
        @Param("nombre") String nombre,
        @Param("email") String email,
        @Param("celular") String celular,
        @Param("cedula") String cedula
    );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuario WHERE id = :id", nativeQuery = true)
    void eliminarUsuario(@Param("id") Long id);
}
