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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReVueltaBack.dtos.estadoPrenda.EstadoPrendaRequestDTO;
import com.example.ReVueltaBack.dtos.estadoPrenda.EstadoPrendaResponseDTO;
import com.example.ReVueltaBack.servicios.EstadoPrenda.IServicioEstadoPrenda;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/estados-prenda")
@Tag(name = "Estados de prenda", description = "Gestión de estados de prenda (CRUD)")
public class EstadoPrendaControlador {

    private final IServicioEstadoPrenda servicioEstadoPrenda;

    public EstadoPrendaControlador(IServicioEstadoPrenda servicioEstadoPrenda) {
        this.servicioEstadoPrenda = servicioEstadoPrenda;
    }

    @PostMapping
    @Operation(summary = "Crear estado de prenda", description = "Registra un nuevo estado de prenda")
    public ResponseEntity<EstadoPrendaResponseDTO> registrar(@RequestBody EstadoPrendaRequestDTO datos) {
        return ResponseEntity.ok(servicioEstadoPrenda.registrar(datos));
    }

    @GetMapping
    @Operation(summary = "Listar estados de prenda", description = "Devuelve todos los estados de prenda registrados")
    public ResponseEntity<List<EstadoPrendaResponseDTO>> listar() {
        return ResponseEntity.ok(servicioEstadoPrenda.listar());
    }

    @GetMapping("/requieren-revision")
    @Operation(summary = "Listar estados que requieren revisión")
    public ResponseEntity<List<EstadoPrendaResponseDTO>> listarQueRequierenRevision() {
        return ResponseEntity.ok(servicioEstadoPrenda.listarQueRequierenRevision());
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar estados por nombre")
    public ResponseEntity<List<EstadoPrendaResponseDTO>> buscarPorNombre(@RequestParam String texto) {
        return ResponseEntity.ok(servicioEstadoPrenda.buscarPorNombre(texto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar estado de prenda por id")
    public ResponseEntity<EstadoPrendaResponseDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(servicioEstadoPrenda.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar estado de prenda")
    public ResponseEntity<EstadoPrendaResponseDTO> actualizar(@RequestBody EstadoPrendaRequestDTO datos,
                                                              @PathVariable UUID id) {
        return ResponseEntity.ok(servicioEstadoPrenda.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar estado de prenda")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        servicioEstadoPrenda.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
