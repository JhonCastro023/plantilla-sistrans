package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import uniandes.edu.co.proyecto.model.UsuarioConductor;
import uniandes.edu.co.proyecto.repository.UsuarioConductorRepository;

@RestController
@RequestMapping("/usuarios/conductores")
public class UsuarioConductorController {

    @Autowired
    private UsuarioConductorRepository usuarioConductorRepository;

    @GetMapping
    public ResponseEntity<Collection<UsuarioConductor>> darUsuariosConductores() {
        try {
            Collection<UsuarioConductor> usuarios = usuarioConductorRepository.darUsuariosConductores();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioConductor> darUsuarioConductor(@PathVariable("id") Long id) {
        try {
            UsuarioConductor usuario = usuarioConductorRepository.darUsuarioConductor(id);
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
    public ResponseEntity<String> guardarUsuarioConductor(@RequestBody UsuarioConductor usuario) {
        try {
            usuarioConductorRepository.insertarUsuarioConductor(
                    usuario.getNombre(),
                    usuario.getNumeroLicencia(),
                    usuario.getVehiculo()
            );
            return new ResponseEntity<>("UsuarioConductor creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el usuario conductor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> editarUsuarioConductor(@PathVariable("id") Long id, @RequestBody UsuarioConductor usuario) {
        try {
            usuarioConductorRepository.actualizarUsuarioConductor(
                    id,
                    usuario.getNombre(),
                    usuario.getNumeroLicencia(),
                    usuario.getVehiculo()
            );
            return new ResponseEntity<>("UsuarioConductor actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el usuario conductor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarUsuarioConductor(@PathVariable("id") Long id) {
        try {
            usuarioConductorRepository.eliminarUsuarioConductor(id);
            return new ResponseEntity<>("UsuarioConductor eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el usuario conductor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/{id}/vehiculo/save")
    public ResponseEntity<String> registrarVehiculo(
            @PathVariable("id") Long id,
            @RequestBody Map<String, String> body) {
        try {
            String vehiculo = body.get("vehiculo");
            usuarioConductorRepository.registrarVehiculo(id, vehiculo);
            return new ResponseEntity<>("Vehículo registrado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar vehículo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
