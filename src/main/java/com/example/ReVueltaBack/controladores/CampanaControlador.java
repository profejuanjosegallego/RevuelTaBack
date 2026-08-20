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

import com.example.ReVueltaBack.dtos.campana.CampanaRequestDTO;
import com.example.ReVueltaBack.dtos.campana.CampanaResponseDTO;
import com.example.ReVueltaBack.servicios.campana.IServicioCampana;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/campanas")
@Tag(name = "Campañas", description = "Gestión de campañas de descuento (CRUD)")
public class CampanaControlador {

    private final IServicioCampana servicioCampana;

    public CampanaControlador(IServicioCampana servicioCampana) {
        this.servicioCampana = servicioCampana;
    }

    @PostMapping
    @Operation(summary = "Crear campaña", description = "Registra una nueva campaña de descuento")
    public ResponseEntity<CampanaResponseDTO> registrar(@RequestBody CampanaRequestDTO datos) {
        CampanaResponseDTO campanaCreada = servicioCampana.registrar(datos);
        return ResponseEntity.ok(campanaCreada);
    }

    @GetMapping
    @Operation(summary = "Listar campañas", description = "Devuelve todas las campañas registradas")
    public ResponseEntity<List<CampanaResponseDTO>> listar() {
        return ResponseEntity.ok(servicioCampana.listar());
    }

    @GetMapping("/activas")
    @Operation(summary = "Listar campañas activas", description = "Devuelve solo las campañas marcadas como activas")
    public ResponseEntity<List<CampanaResponseDTO>> listarActivas() {
        return ResponseEntity.ok(servicioCampana.listarActivas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar campaña por id")
    public ResponseEntity<CampanaResponseDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(servicioCampana.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar campaña")
    public ResponseEntity<CampanaResponseDTO> actualizar(@RequestBody CampanaRequestDTO datos, @PathVariable UUID id) {
        return ResponseEntity.ok(servicioCampana.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar campaña")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        servicioCampana.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
