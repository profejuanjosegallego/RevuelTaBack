package com.example.ReVueltaBack.controladores;

import com.example.ReVueltaBack.dtos.Reseña.ReseñaRequestDTO;
import com.example.ReVueltaBack.dtos.Reseña.ReseñaResponseDTO;
import com.example.ReVueltaBack.servicios.Reseña.IReseñaServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// HU COM-C01 — Controlador REST de Reseñas.
// La RUTA va sin eñe ni tildes ("/api/resenas"): las URL se escriben en ASCII
// para que el navegador no tenga que codificarlas (/api/rese%C3%B1as). Los
// nombres de las clases y los campos del JSON sí conservan la eñe.
@RestController
@RequestMapping("/api/resenas")
@Tag(name = "Reseñas", description = "Reseñas que un usuario le escribe a otro (CRUD)")
public class ReseñaControlador {

    private final IReseñaServicio servicioReseña;

    public ReseñaControlador(IReseñaServicio servicioReseña) {
        this.servicioReseña = servicioReseña;
    }


    @PostMapping
    @Operation(summary = "Crear reseña", description = "Requiere idAutor e idUsuarioReseñado; ambos deben existir")
    public ResponseEntity<ReseñaResponseDTO> crear(@RequestBody ReseñaRequestDTO dto){
        ReseñaResponseDTO reseñaCreada=servicioReseña.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reseñaCreada);
    }

    @GetMapping
    @Operation(summary = "Listar reseñas")
    public ResponseEntity<List<ReseñaResponseDTO>>listar(){
        return ResponseEntity.ok(servicioReseña.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar reseña por id")
    public ResponseEntity<ReseñaResponseDTO>buscarPorId(@PathVariable UUID id){
        return ResponseEntity.ok(servicioReseña.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar reseña")
    public ResponseEntity<ReseñaResponseDTO>actualizar(@RequestBody ReseñaRequestDTO datos, @PathVariable UUID id){
        return ResponseEntity.ok(servicioReseña.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reseña")
    public ResponseEntity<Void>eliminar(@PathVariable UUID id){
        servicioReseña.eliminar(id);
        return ResponseEntity.noContent().build();

    }
}
