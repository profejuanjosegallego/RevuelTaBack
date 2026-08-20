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

import com.example.ReVueltaBack.dtos.categoria.CategoriaRequestDTO;
import com.example.ReVueltaBack.dtos.categoria.CategoriaResponseDTO;
import com.example.ReVueltaBack.servicios.categoria.IServicioCategoria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

// HU CAT-C02 — Controlador REST de Categorias.
@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Gestion del catalogo de categorias de prendas (CRUD)")
public class CategoriaControlador {

    private final IServicioCategoria servicioCategoria;

    public CategoriaControlador(IServicioCategoria servicioCategoria) {
        this.servicioCategoria = servicioCategoria;
    }

    @PostMapping
    @Operation(summary = "Crear categoria")
    public ResponseEntity<CategoriaResponseDTO> registrar(@RequestBody CategoriaRequestDTO datos) {
        CategoriaResponseDTO categoriaCreada = servicioCategoria.crear(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCreada);
    }

    @GetMapping
    @Operation(summary = "Listar categorias")
    public ResponseEntity<List<CategoriaResponseDTO>> listar() {
        return ResponseEntity.ok(servicioCategoria.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por id")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(servicioCategoria.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categoria")
    public ResponseEntity<CategoriaResponseDTO> actualizar(@PathVariable UUID id,
                                                           @RequestBody CategoriaRequestDTO datos) {
        return ResponseEntity.ok(servicioCategoria.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoria")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        servicioCategoria.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
