package uniandes.edu.co.proyecto.controller;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;

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
    public ResponseEntity<String> guardarSolicitud(@RequestBody Map<String, Object> body) {
        try {
            Long idUsuario = body.get("idUsuario") != null ? Long.valueOf(body.get("idUsuario").toString()) : null;
            if (idUsuario == null) {
                throw new IllegalArgumentException("idUsuario no puede ser null");
            }
            String tipo = (String) body.get("tipo");
            String nivel = (String) body.get("nivel");
            LocalDateTime solicitadoEn = LocalDateTime.parse((String) body.get("solicitadoEn"));
            Double distanciaKm = Double.valueOf(body.get("distanciaKm").toString());
            Double tarifaCalculada = Double.valueOf(body.get("tarifaCalculada").toString());
            String estado = (String) body.get("estado");
            Long idMedioPago = body.get("idMedioPago") != null ? Long.valueOf(body.get("idMedioPago").toString()) : null;
            Long idTarifa = body.get("idTarifa") != null ? Long.valueOf(body.get("idTarifa").toString()) : null;

            solicitudServicioRepository.insertarSolicitudServicio(
                    idUsuario,
                    tipo,
                    nivel,
                    solicitadoEn,
                    distanciaKm,
                    tarifaCalculada,
                    estado,
                    idMedioPago,
                    idTarifa
            );

            return new ResponseEntity<>("SolicitudServicio creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la solicitud: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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

    
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<String> finalizarServicio(
            @PathVariable("id") Long id,
            @RequestBody Map<String, Object> body) {

        try {
            Double distanciaFinal = Double.valueOf(body.get("distanciaFinal").toString());
            Double tarifaFinal = Double.valueOf(body.get("tarifaFinal").toString());

            LocalDateTime finalizadoEn = LocalDateTime.now();

            int filas = solicitudServicioRepository.finalizarServicio(
                    id,
                    distanciaFinal,
                    tarifaFinal,
                    finalizadoEn
            );

            if (filas == 0) {
                return ResponseEntity.badRequest().body(
                        "No se pudo finalizar: no existe o no está en estado 'en_curso'"
                );
            }

            return ResponseEntity.ok("Servicio finalizado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al finalizar: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/finalizar")
    public ResponseEntity<String> finalizarSolicitud(
            @PathVariable("id") Long id,
            @RequestBody Map<String, Object> body) {

        try {
            Double distanciaFinal = Double.valueOf(body.get("distanciaFinal").toString());
            Double tarifaFinal = Double.valueOf(body.get("tarifaFinal").toString());
            LocalDateTime finalizadoEn = LocalDateTime.now();

            int filasActualizadas = solicitudServicioRepository.finalizarSolicitud(
                    id,
                    finalizadoEn,
                    distanciaFinal,
                    tarifaFinal
            );

            if (filasActualizadas == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No se pudo finalizar: no existe o no está en estado 'en_curso'");
            }

            return ResponseEntity.ok("Solicitud finalizada correctamente");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al finalizar la solicitud: " + e.getMessage());
        }
    }



}
