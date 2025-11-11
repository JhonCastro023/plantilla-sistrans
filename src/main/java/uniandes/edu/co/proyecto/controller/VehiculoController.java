package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.model.Vehiculo;
import uniandes.edu.co.proyecto.repository.VehiculoRepository;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping
    public ResponseEntity<Collection<Vehiculo>> darVehiculos() {
        try {
            Collection<Vehiculo> vehiculos = vehiculoRepository.darVehiculos();
            return ResponseEntity.ok(vehiculos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> darVehiculo(@PathVariable("id") Long id) {
        try {
            Vehiculo vehiculo = vehiculoRepository.darVehiculo(id);
            if (vehiculo != null) {
                return ResponseEntity.ok(vehiculo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> guardarVehiculo(@RequestBody Vehiculo vehiculo) {
        try {
            vehiculoRepository.insertarVehiculo(
                    vehiculo.getPlaca(),
                    vehiculo.getMarca(),
                    vehiculo.getModelo(),
                    vehiculo.getCapacidad(),
                    vehiculo.getConductorId()
            );
            return new ResponseEntity<>("Vehículo creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el vehículo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> editarVehiculo(@PathVariable("id") Long id, @RequestBody Vehiculo vehiculo) {
        try {
            vehiculoRepository.actualizarVehiculo(
                    id,
                    vehiculo.getPlaca(),
                    vehiculo.getMarca(),
                    vehiculo.getModelo(),
                    vehiculo.getCapacidad(),
                    vehiculo.getConductorId()
            );
            return new ResponseEntity<>("Vehículo actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el vehículo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarVehiculo(@PathVariable("id") Long id) {
        try {
            vehiculoRepository.eliminarVehiculo(id);
            return new ResponseEntity<>("Vehículo eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el vehículo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
