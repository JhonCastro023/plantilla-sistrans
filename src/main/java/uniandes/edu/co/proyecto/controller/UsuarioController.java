package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.model.Usuario;
import uniandes.edu.co.proyecto.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<Collection<Usuario>> darUsuarios() {
        try {
            Collection<Usuario> usuarios = usuarioRepository.darUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> darUsuario(@PathVariable("id") Long id) {
        try {
            Usuario usuario = usuarioRepository.darUsuario(id);
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
    public ResponseEntity<String> guardarUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioRepository.insertarUsuario(
                    usuario.getNombre(),
                    usuario.getEmail(),
                    usuario.getCelular(),
                    usuario.getCedula()
            );
            return new ResponseEntity<>("Usuario creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> editarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        try {
            usuarioRepository.actualizarUsuario(
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getEmail(),
                    usuario.getCelular(),
                    usuario.getCedula()
            );
            return new ResponseEntity<>("Usuario actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") Long id) {
        try {
            usuarioRepository.eliminarUsuario(id);
            return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
