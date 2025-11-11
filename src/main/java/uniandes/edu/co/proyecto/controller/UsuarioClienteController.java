package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.model.UsuarioCliente;
import uniandes.edu.co.proyecto.repository.UsuarioClienteRepository;

@RestController
@RequestMapping("/usuarios/clientes")
public class UsuarioClienteController {

    @Autowired
    private UsuarioClienteRepository usuarioClienteRepository;

    @GetMapping
    public ResponseEntity<Collection<UsuarioCliente>> darUsuariosClientes() {
        try {
            Collection<UsuarioCliente> usuarios = usuarioClienteRepository.darUsuariosClientes();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioCliente> darUsuarioCliente(@PathVariable("id") Long id) {
        try {
            UsuarioCliente usuario = usuarioClienteRepository.darUsuarioCliente(id);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> guardarUsuarioCliente(@RequestBody UsuarioCliente usuario) {
        try {
            usuarioClienteRepository.insertarUsuarioCliente(usuario.getNombre(), usuario.getCorreo(),usuario.getTelefono());
            return new ResponseEntity<>("UsuarioCliente creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el usuario cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> editarUsuarioCliente(@PathVariable("id") Long id, @RequestBody UsuarioCliente usuario) {
        try {
            usuarioClienteRepository.actualizarUsuarioCliente(id, usuario.getNombre(), usuario.getCorreo(), usuario.getTelefono());
            return new ResponseEntity<>("UsuarioCliente actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el usuario cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarUsuarioCliente(@PathVariable("id") Long id) {
        try {
            usuarioClienteRepository.eliminarUsuarioCliente(id);
            return new ResponseEntity<>("UsuarioCliente eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el usuario cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
