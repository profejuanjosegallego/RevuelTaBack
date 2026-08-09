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

import com.example.ReVueltaBack.dtos.Campana.CampanaRequestDTO;
import com.example.ReVueltaBack.dtos.Campana.CampanaResponseDTO;
import com.example.ReVueltaBack.servicios.Campana.IServicioCampana;

@RestController
@RequestMapping("/api/campanas")
public class CampanaControlador {

    private final IServicioCampana servicioCampana;

    public CampanaControlador(IServicioCampana servicioCampana) {
        this.servicioCampana = servicioCampana;
    }

    @PostMapping
    public ResponseEntity<CampanaResponseDTO> registrar(@RequestBody CampanaRequestDTO datos) {
        CampanaResponseDTO campanaCreada = servicioCampana.registrar(datos);
        return ResponseEntity.ok(campanaCreada);
    }

    @GetMapping
    public ResponseEntity<List<CampanaResponseDTO>> listar() {
        return ResponseEntity.ok(servicioCampana.listar());
    }

    @GetMapping("/activas")
    public ResponseEntity<List<CampanaResponseDTO>> listarActivas() {
        return ResponseEntity.ok(servicioCampana.listarActivas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanaResponseDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(servicioCampana.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampanaResponseDTO> actualizar(@RequestBody CampanaRequestDTO datos, @PathVariable UUID id) {
        return ResponseEntity.ok(servicioCampana.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        servicioCampana.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}