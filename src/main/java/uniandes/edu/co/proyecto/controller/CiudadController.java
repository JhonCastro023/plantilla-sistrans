package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.model.Ciudad;
import uniandes.edu.co.proyecto.repository.CiudadRepository;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping
    public ResponseEntity<Collection<Ciudad>> darCiudades() {
        try {
            Collection<Ciudad> ciudades = ciudadRepository.darCiudades();
            return ResponseEntity.ok(ciudades);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> darCiudad(@PathVariable("id") Long id) {
        try {
            Ciudad ciudad = ciudadRepository.darCiudad(id);
            if (ciudad != null) {
                return ResponseEntity.ok(ciudad);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> guardarCiudad(@RequestBody Ciudad ciudad) {
        try {
            ciudadRepository.insertarCiudad(ciudad.getNombre());
            return new ResponseEntity<>("Ciudad creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> editarCiudad(@PathVariable("id") Long id, @RequestBody Ciudad ciudad) {
        try {
            ciudadRepository.actualizarCiudad(id, ciudad.getNombre());
            return new ResponseEntity<>("Ciudad actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarCiudad(@PathVariable("id") Long id) {
        try {
            ciudadRepository.eliminarCiudad(id);
            return new ResponseEntity<>("Ciudad eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
