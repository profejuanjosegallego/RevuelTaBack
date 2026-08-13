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

import com.example.ReVueltaBack.dtos.cupon.CuponRequestDTO;
import com.example.ReVueltaBack.dtos.cupon.CuponResponseDTO;
import com.example.ReVueltaBack.servicios.cupon.IServicioCupon;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cupones")
@Tag(name = "Cupones", description = "Gestión de cupones asociados a una campaña (CRUD)")
public class CuponControlador {

    private final IServicioCupon servicioCupon;

    public CuponControlador(IServicioCupon servicioCupon) {
        this.servicioCupon = servicioCupon;
    }

    @PostMapping
    @Operation(summary = "Crear cupón", description = "Registra un nuevo cupón asociado a una campaña existente")
    public ResponseEntity<CuponResponseDTO> registrar(@RequestBody CuponRequestDTO datos) {
        CuponResponseDTO cuponCreado = servicioCupon.registrar(datos);
        return ResponseEntity.ok(cuponCreado);
    }

    @GetMapping
    @Operation(summary = "Listar cupones", description = "Devuelve todos los cupones registrados")
    public ResponseEntity<List<CuponResponseDTO>> listar() {
        return ResponseEntity.ok(servicioCupon.listar());
    }

    @GetMapping("/validos")
    @Operation(summary = "Listar cupones vigentes", description = "Cupones no vencidos y con usos disponibles")
    public ResponseEntity<List<CuponResponseDTO>> listarValidos() {
        return ResponseEntity.ok(servicioCupon.listarValidos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cupón por id")
    public ResponseEntity<CuponResponseDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(servicioCupon.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cupón")
    public ResponseEntity<CuponResponseDTO> actualizar(@RequestBody CuponRequestDTO datos, @PathVariable UUID id) {
        return ResponseEntity.ok(servicioCupon.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cupón")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        servicioCupon.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
