package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.model.SolicitudServicio;
import uniandes.edu.co.proyecto.repository.SolicitudServicioRepository;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudServicioController {

    @Autowired
    private SolicitudServicioRepository solicitudServicioRepository;

    @GetMapping
    public ResponseEntity<Collection<SolicitudServicio>> darSolicitudes() {
        try {
            Collection<SolicitudServicio> solicitudes = solicitudServicioRepository.darSolicitudesServicio();
            return ResponseEntity.ok(solicitudes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudServicio> darSolicitud(@PathVariable("id") Long id) {
        try {
            SolicitudServicio solicitud = solicitudServicioRepository.darSolicitudServicio(id);
            if (solicitud != null) {
                return ResponseEntity.ok(solicitud);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> guardarSolicitud(@RequestBody SolicitudServicio solicitud) {
        try {
            // Convertir fecha a String para la query nativa
            String fecha = solicitud.getFechaSolicitud().toString();
            solicitudServicioRepository.insertarSolicitudServicio(
                    solicitud.getOrigen(),
                    solicitud.getDestino(),
                    fecha
            );
            return new ResponseEntity<>("SolicitudServicio creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> editarSolicitud(@PathVariable("id") Long id, @RequestBody SolicitudServicio solicitud) {
        try {
            String fecha = solicitud.getFechaSolicitud().toString();
            solicitudServicioRepository.actualizarSolicitudServicio(
                    id,
                    solicitud.getOrigen(),
                    solicitud.getDestino(),
                    fecha
            );
            return new ResponseEntity<>("SolicitudServicio actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarSolicitud(@PathVariable("id") Long id) {
        try {
            solicitudServicioRepository.eliminarSolicitudServicio(id);
            return new ResponseEntity<>("SolicitudServicio eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
