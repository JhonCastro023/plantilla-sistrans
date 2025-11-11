package uniandes.edu.co.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.UsuarioCliente;

import java.util.Collection;

public interface UsuarioClienteRepository extends JpaRepository<UsuarioCliente, Long> {

    // Consultar todos
    @Query(value = "SELECT * FROM usuario_cliente", nativeQuery = true)
    Collection<UsuarioCliente> darUsuariosClientes();

    // Consultar por ID
    @Query(value = "SELECT * FROM usuario_cliente WHERE id = :id", nativeQuery = true)
    UsuarioCliente darUsuarioCliente(@Param("id") Long id);

    // Consultar por correo
    @Query(value = "SELECT * FROM usuario_cliente WHERE correo = :correo", nativeQuery = true)
    UsuarioCliente darUsuarioClientePorCorreo(@Param("correo") String correo);

    // Insertar
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuario_cliente (id, nombre, correo, telefono) VALUES (alpescab_seq.nextval, :nombre, :correo, :telefono)", nativeQuery = true)
    void insertarUsuarioCliente(@Param("nombre") String nombre, @Param("correo") String correo, @Param("telefono") String telefono);

    // Actualizar
    @Modifying
    @Transactional
    @Query(value = "UPDATE usuario_cliente SET nombre = :nombre, correo = :correo, telefono = :telefono WHERE id = :id", nativeQuery = true)
    void actualizarUsuarioCliente(@Param("id") Long id, @Param("nombre") String nombre, @Param("correo") String correo, @Param("telefono") String telefono);

    // Eliminar
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuario_cliente WHERE id = :id", nativeQuery = true)
    void eliminarUsuarioCliente(@Param("id") Long id);
}
