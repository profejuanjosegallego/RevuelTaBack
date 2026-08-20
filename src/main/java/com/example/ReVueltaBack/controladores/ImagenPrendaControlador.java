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

import com.example.ReVueltaBack.dtos.imagenprenda.ImagenPrendaRequestDTO;
import com.example.ReVueltaBack.dtos.imagenprenda.ImagenPrendaResponseDTO;
import com.example.ReVueltaBack.servicios.imagenprenda.IServicioImagenPrenda;

@RestController
@RequestMapping("/api/imagenes-prenda")
public class ImagenPrendaControlador {

    private final IServicioImagenPrenda imagenesPrendaServicio;

    public ImagenPrendaControlador(IServicioImagenPrenda imagenesPrendaServicio) {
        this.imagenesPrendaServicio = imagenesPrendaServicio;
    }

    @PostMapping
    public ResponseEntity<ImagenPrendaResponseDTO> crear(@RequestBody ImagenPrendaRequestDTO requestDTO) {
        ImagenPrendaResponseDTO creada = imagenesPrendaServicio.crear(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenPrendaResponseDTO> obtenerPorId(@PathVariable UUID id) {
        ImagenPrendaResponseDTO imagen = imagenesPrendaServicio.obtenerPorId(id);
        return ResponseEntity.ok(imagen);
    }

    @GetMapping
    public ResponseEntity<List<ImagenPrendaResponseDTO>> listarTodas() {
        List<ImagenPrendaResponseDTO> imagenes = imagenesPrendaServicio.listarTodas();
        return ResponseEntity.ok(imagenes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagenPrendaResponseDTO> actualizar(@PathVariable UUID id,
                                                                  @RequestBody ImagenPrendaRequestDTO requestDTO) {
        ImagenPrendaResponseDTO actualizada = imagenesPrendaServicio.actualizar(id, requestDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        imagenesPrendaServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}