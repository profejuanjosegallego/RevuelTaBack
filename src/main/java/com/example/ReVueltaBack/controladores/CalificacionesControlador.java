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

import com.example.ReVueltaBack.dtos.calificaciones.CalificacionesRequestDTO;
import com.example.ReVueltaBack.dtos.calificaciones.CalificacionesResponseDTO;
import com.example.ReVueltaBack.servicios.calificaciones.ICalificacionesServicio;

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionesControlador {

    private final ICalificacionesServicio calificacionesServicio;

    public CalificacionesControlador(ICalificacionesServicio calificacionesServicio) {
        this.calificacionesServicio = calificacionesServicio;
    }

    @PostMapping
    public ResponseEntity<CalificacionesResponseDTO> crear(
            @RequestBody CalificacionesRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(calificacionesServicio.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<CalificacionesResponseDTO>> listar() {
        return ResponseEntity.ok(calificacionesServicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificacionesResponseDTO> buscarPorId(
            @PathVariable UUID id) {
        return ResponseEntity.ok(calificacionesServicio.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalificacionesResponseDTO> actualizar(
            @PathVariable UUID id,
            @RequestBody CalificacionesRequestDTO dto) {
        return ResponseEntity.ok(calificacionesServicio.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        calificacionesServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}