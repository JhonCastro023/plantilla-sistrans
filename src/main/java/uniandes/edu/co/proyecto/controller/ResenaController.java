package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.model.Resena;
import uniandes.edu.co.proyecto.repository.ResenaRepository;

@RestController
@RequestMapping("/resenas")
public class ResenaController {

    @Autowired
    private ResenaRepository resenaRepository;

    @GetMapping
    public ResponseEntity<Collection<Resena>> darResenas() {
        try {
            Collection<Resena> resenas = resenaRepository.darResenas();
            return ResponseEntity.ok(resenas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> darResena(@PathVariable("id") Long id) {
        try {
            Resena resena = resenaRepository.darResena(id);
            if (resena != null) {
                return ResponseEntity.ok(resena);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> guardarResena(@RequestBody Resena resena) {
        try {
            resenaRepository.insertarResena(
                    resena.getViaje().getId(),
                    resena.getComentario(),
                    resena.getCalificacion()
                    
                    
            );
            return new ResponseEntity<>("Reseña creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la reseña", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> editarResena(@PathVariable("id") Long id, @RequestBody Resena resena) {
        try {
            resenaRepository.actualizarResena(
                    resena.getId(),
                    resena.getComentario(),
                    resena.getCalificacion()
                    
            );
            return new ResponseEntity<>("Reseña actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la reseña", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarResena(@PathVariable("id") Long id) {
        try {
            resenaRepository.eliminarResena(id);
            return new ResponseEntity<>("Reseña eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la reseña", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
