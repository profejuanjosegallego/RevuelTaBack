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

import com.example.ReVueltaBack.dtos.recompensa.RecompensaRequestDTO;
import com.example.ReVueltaBack.dtos.recompensa.RecompensaResponseDTO;
import com.example.ReVueltaBack.servicios.recompensa.IServicioRecompensa;

@RestController
@RequestMapping("/api/recompensas")
public class RecompensasControlador {

    private final IServicioRecompensa servicioRecompensa;

    public RecompensasControlador(IServicioRecompensa servicioRecompensa) {
        this.servicioRecompensa = servicioRecompensa;
    }

    @PostMapping
    public ResponseEntity<RecompensaResponseDTO> crear(@RequestBody RecompensaRequestDTO dto) {
        RecompensaResponseDTO creada = servicioRecompensa.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping
    public ResponseEntity<List<RecompensaResponseDTO>> listar() {
        List<RecompensaResponseDTO> lista = servicioRecompensa.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecompensaResponseDTO> buscarPorId(@PathVariable UUID id) {
        RecompensaResponseDTO recompensa = servicioRecompensa.buscarPorId(id);
        return ResponseEntity.ok(recompensa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecompensaResponseDTO> actualizar(@PathVariable UUID id, @RequestBody RecompensaRequestDTO dto) {
        RecompensaResponseDTO actualizada = servicioRecompensa.actualizar(id, dto);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        servicioRecompensa.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


