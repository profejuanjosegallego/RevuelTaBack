package com.example.ReVueltaBack.controladores;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReVueltaBack.dtos.calificacion.CalificacionRequestDTO;
import com.example.ReVueltaBack.dtos.calificacion.CalificacionResponseDTO;
import com.example.ReVueltaBack.servicios.calificacion.IServicioCalificacion;

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionControlador {

    private final IServicioCalificacion calificacionesServicio;

    public CalificacionControlador(IServicioCalificacion calificacionesServicio) {
        this.calificacionesServicio = calificacionesServicio;
    }

    @PostMapping
    public ResponseEntity<CalificacionResponseDTO> crear(
            @RequestBody CalificacionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(calificacionesServicio.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<CalificacionResponseDTO>> listar() {
        return ResponseEntity.ok(calificacionesServicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificacionResponseDTO> buscarPorId(
            @PathVariable UUID id) {
        return ResponseEntity.ok(calificacionesServicio.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalificacionResponseDTO> actualizar(
            @PathVariable UUID id,
            @RequestBody CalificacionRequestDTO dto) {
        return ResponseEntity.ok(calificacionesServicio.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        calificacionesServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}