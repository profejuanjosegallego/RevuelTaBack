package com.example.ReVueltaBack.controladores;

import com.example.ReVueltaBack.dtos.resena.ResenaDetalleResponseDTO;
import com.example.ReVueltaBack.dtos.resena.ResenaRequestDTO;
import com.example.ReVueltaBack.dtos.resena.ResenaResponseDTO;
import com.example.ReVueltaBack.servicios.resena.IServicioResena;

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
public class ResenaControlador {

    private final IServicioResena servicioResena;

    public ResenaControlador(IServicioResena servicioResena) {
        this.servicioResena = servicioResena;
    }


    @PostMapping
    @Operation(summary = "Crear reseña", description = "Requiere idAutor e idUsuarioReseñado; ambos deben existir")
    public ResponseEntity<ResenaResponseDTO> crear(@RequestBody ResenaRequestDTO dto){
        ResenaResponseDTO resenaCreada=servicioResena.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resenaCreada);
    }

    @GetMapping
    @Operation(summary = "Listar reseñas")
    public ResponseEntity<List<ResenaResponseDTO>>listar(){
        return ResponseEntity.ok(servicioResena.listar());
    }

    @GetMapping("/usuariosResenado/{id}")
    @Operation(summary = "Listar reseñas de un usuario reseñado")
    public ResponseEntity<List<ResenaDetalleResponseDTO>> listarPorUsuarioResenado(@PathVariable UUID id){
        return ResponseEntity.ok(servicioResena.listarPorUsuarioResenado(id));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar reseña por id")
    public ResponseEntity<ResenaResponseDTO>buscarPorId(@PathVariable UUID id){
        return ResponseEntity.ok(servicioResena.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar reseña")
    public ResponseEntity<ResenaResponseDTO>actualizar(@RequestBody ResenaRequestDTO datos, @PathVariable UUID id){
        return ResponseEntity.ok(servicioResena.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reseña")
    public ResponseEntity<Void>eliminar(@PathVariable UUID id){
        servicioResena.eliminar(id);
        return ResponseEntity.noContent().build();

    }
}
