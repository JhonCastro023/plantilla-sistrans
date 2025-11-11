package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.model.MedioPago;
import uniandes.edu.co.proyecto.repository.MedioPagoRepository;

@RestController
@RequestMapping("/mediospago")
public class MedioPagoController {

    @Autowired
    private MedioPagoRepository medioPagoRepository;

    @GetMapping
    public ResponseEntity<Collection<MedioPago>> darMediosPago() {
        try {
            Collection<MedioPago> medios = medioPagoRepository.darMediosPago();
            return ResponseEntity.ok(medios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedioPago> darMedioPago(@PathVariable("id") Long id) {
        try {
            MedioPago medio = medioPagoRepository.darMedioPago(id);
            if (medio != null) {
                return ResponseEntity.ok(medio);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> guardarMedioPago(@RequestBody MedioPago medio) {
        try {
            medioPagoRepository.insertarMedioPago(
                    medio.getTipo(),
                    medio.getDetalles()
            );
            return new ResponseEntity<>("MedioPago creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el medio de pago", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> editarMedioPago(@PathVariable("id") Long id, @RequestBody MedioPago medio) {
        try {
            medioPagoRepository.actualizarMedioPago(
                    id,
                    medio.getTipo(),
                    medio.getDetalles()
            );
            return new ResponseEntity<>("MedioPago actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el medio de pago", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarMedioPago(@PathVariable("id") Long id) {
        try {
            medioPagoRepository.eliminarMedioPago(id);
            return new ResponseEntity<>("MedioPago eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el medio de pago", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
