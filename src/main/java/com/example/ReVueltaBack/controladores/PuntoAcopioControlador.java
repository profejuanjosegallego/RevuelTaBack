package com.example.ReVueltaBack.controladores;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReVueltaBack.dtos.PuntoAcopio.PuntoAcopioRequestDTO;
import com.example.ReVueltaBack.dtos.PuntoAcopio.PuntoAcopioResponseDTO;
import com.example.ReVueltaBack.servicios.PuntoAcopio.IPuntoAcopioServicio;

@RestController
@RequestMapping("/api/puntos-acopio")
public class PuntoAcopioControlador {

    private final IPuntoAcopioServicio servicioPuntoAcopio;

    public PuntoAcopioControlador(IPuntoAcopioServicio servicioPuntoAcopio) {
        this.servicioPuntoAcopio = servicioPuntoAcopio;
    }

    @PostMapping
    public ResponseEntity<PuntoAcopioResponseDTO> crear(@RequestBody PuntoAcopioRequestDTO datos) {
        PuntoAcopioResponseDTO puntoAcopioCreado = servicioPuntoAcopio.crear(datos);
        return ResponseEntity.status(201).body(puntoAcopioCreado);
    }

    @GetMapping
    public ResponseEntity<List<PuntoAcopioResponseDTO>> listar() {
        return ResponseEntity.ok(servicioPuntoAcopio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuntoAcopioResponseDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(servicioPuntoAcopio.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PuntoAcopioResponseDTO> actualizar(@RequestBody PuntoAcopioRequestDTO datos, @PathVariable UUID id) {
        return ResponseEntity.ok(servicioPuntoAcopio.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        servicioPuntoAcopio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}