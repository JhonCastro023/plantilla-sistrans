package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.model.Viaje;
import uniandes.edu.co.proyecto.repository.ViajeRepository;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    @Autowired
    private ViajeRepository viajeRepository;

    @GetMapping
    public ResponseEntity<Collection<Viaje>> darViajes() {
        try {
            Collection<Viaje> viajes = viajeRepository.darViajes();
            return ResponseEntity.ok(viajes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaje> darViaje(@PathVariable("id") Long id) {
        try {
            Viaje viaje = viajeRepository.darViaje(id);
            if (viaje != null) {
                return ResponseEntity.ok(viaje);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> guardarViaje(@RequestBody Viaje viaje) {
        try {
            viajeRepository.insertarViaje(
                    viaje.getCliente().getId(),
                    viaje.getConductor().getId(),
                    viaje.getFechaInicio().toString(),
                    "pendiente" // o el estado inicial que quieras
            );
            return new ResponseEntity<>("Viaje creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el viaje", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> editarViaje(@PathVariable("id") Long id, @RequestBody Viaje viaje) {
        try {
            viajeRepository.actualizarViaje(id, "finalizado"); // actualizar estado según la lógica
            return new ResponseEntity<>("Viaje actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el viaje", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarViaje(@PathVariable("id") Long id) {
        try {
            viajeRepository.eliminarViaje(id);
            return new ResponseEntity<>("Viaje eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el viaje", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
