package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.model.PuntoGeografico;
import uniandes.edu.co.proyecto.repository.PuntoGeograficoRepository;

@RestController
@RequestMapping("/puntosgeograficos")
public class PuntoGeograficoController {

    @Autowired
    private PuntoGeograficoRepository puntoGeograficoRepository;

    @GetMapping
    public ResponseEntity<Collection<PuntoGeografico>> darPuntosGeograficos() {
        try {
            Collection<PuntoGeografico> puntos = puntoGeograficoRepository.darPuntosGeograficos();
            return ResponseEntity.ok(puntos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuntoGeografico> darPuntoGeografico(@PathVariable("id") Long id) {
        try {
            PuntoGeografico punto = puntoGeograficoRepository.darPuntoGeografico(id);
            if (punto != null) {
                return ResponseEntity.ok(punto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> guardarPuntoGeografico(@RequestBody PuntoGeografico punto) {
        try {
            puntoGeograficoRepository.insertarPuntoGeografico(
                punto.getNombre(),
                punto.getDireccion(),
                punto.getLatitud(),
                punto.getLongitud(),
                punto.getId()
            );
            return new ResponseEntity<>("PuntoGeografico creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el punto geográfico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> editarPuntoGeografico(@PathVariable("id") Long id, @RequestBody PuntoGeografico punto) {
        try {
            puntoGeograficoRepository.actualizarPuntoGeografico(
                id,
                punto.getNombre(),
                punto.getDireccion(),
                punto.getLatitud(),
                punto.getLongitud(),
                punto.getCiudadId()
            );
            return new ResponseEntity<>("PuntoGeografico actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el punto geográfico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarPuntoGeografico(@PathVariable("id") Long id) {
        try {
            puntoGeograficoRepository.eliminarPuntoGeografico(id);
            return new ResponseEntity<>("PuntoGeografico eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el punto geográfico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
