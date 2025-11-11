package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.model.Tarifa;
import uniandes.edu.co.proyecto.repository.TarifaRepository;

@RestController
@RequestMapping("/tarifas")
public class TarifaController {

    @Autowired
    private TarifaRepository tarifaRepository;

    @GetMapping
    public ResponseEntity<Collection<Tarifa>> darTarifas() {
        try {
            Collection<Tarifa> tarifas = tarifaRepository.darTarifas();
            return ResponseEntity.ok(tarifas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarifa> darTarifa(@PathVariable("id") Long id) {
        try {
            Tarifa tarifa = tarifaRepository.darTarifa(id);
            if (tarifa != null) {
                return ResponseEntity.ok(tarifa);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> guardarTarifa(@RequestBody Tarifa tarifa) {
        try {
            tarifaRepository.insertarTarifa(tarifa.getValorBase(), tarifa.getCostoPorKm());
            return new ResponseEntity<>("Tarifa creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la tarifa", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> editarTarifa(@PathVariable("id") Long id, @RequestBody Tarifa tarifa) {
        try {
            tarifaRepository.actualizarTarifa(id, tarifa.getValorBase(), tarifa.getCostoPorKm());
            return new ResponseEntity<>("Tarifa actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la tarifa", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarTarifa(@PathVariable("id") Long id) {
        try {
            tarifaRepository.eliminarTarifa(id);
            return new ResponseEntity<>("Tarifa eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la tarifa", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
