package uniandes.edu.co.proyecto.controller;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.model.Disponibilidad;
import uniandes.edu.co.proyecto.repository.DisponibilidadRepository;

@RestController
@RequestMapping("/disponibilidades")
public class DisponibilidadController {

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    @GetMapping
    public ResponseEntity<Collection<Disponibilidad>> darDisponibilidades() {
        try {
            Collection<Disponibilidad> disponibilidades = disponibilidadRepository.darDisponibilidades();
            return ResponseEntity.ok(disponibilidades);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disponibilidad> darDisponibilidad(@PathVariable("id") Long id) {
        try {
            Disponibilidad disponibilidad = disponibilidadRepository.darDisponibilidad(id);
            if (disponibilidad != null) {
                return ResponseEntity.ok(disponibilidad);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> guardarDisponibilidad(@RequestBody Disponibilidad disponibilidad) {
        try {
            disponibilidadRepository.insertarDisponibilidad(
                    disponibilidad.getConductor().getId(),        
                    disponibilidad.getFin()
                    
            );
            return new ResponseEntity<>("Disponibilidad creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la disponibilidad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> editarDisponibilidad(@PathVariable("id") Long id, @RequestBody Disponibilidad disponibilidad) {
        try {
            disponibilidadRepository.actualizarDisponibilidad(
                    disponibilidad.getConductor().getId(),
                    disponibilidad.getFin()
                    
            );
            return new ResponseEntity<>("Disponibilidad actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la disponibilidad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarDisponibilidad(@PathVariable("id") Long id) {
        try {
            disponibilidadRepository.eliminarDisponibilidad(id);
            return new ResponseEntity<>("Disponibilidad eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la disponibilidad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
