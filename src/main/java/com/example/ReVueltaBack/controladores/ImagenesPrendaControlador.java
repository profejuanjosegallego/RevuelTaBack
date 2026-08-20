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

import com.example.ReVueltaBack.dtos.imagenprenda.ImagenesPrendaRequestDTO;
import com.example.ReVueltaBack.dtos.imagenprenda.ImagenesPrendaResponseDTO;
import com.example.ReVueltaBack.servicios.imagenprenda.IImagenesPrendaServicio;

@RestController
@RequestMapping("/api/imagenes-prenda")
public class ImagenesPrendaControlador {

    private final IImagenesPrendaServicio imagenesPrendaServicio;

    public ImagenesPrendaControlador(IImagenesPrendaServicio imagenesPrendaServicio) {
        this.imagenesPrendaServicio = imagenesPrendaServicio;
    }

    @PostMapping
    public ResponseEntity<ImagenesPrendaResponseDTO> crear(@RequestBody ImagenesPrendaRequestDTO requestDTO) {
        ImagenesPrendaResponseDTO creada = imagenesPrendaServicio.crear(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenesPrendaResponseDTO> obtenerPorId(@PathVariable UUID id) {
        ImagenesPrendaResponseDTO imagen = imagenesPrendaServicio.obtenerPorId(id);
        return ResponseEntity.ok(imagen);
    }

    @GetMapping
    public ResponseEntity<List<ImagenesPrendaResponseDTO>> listarTodas() {
        List<ImagenesPrendaResponseDTO> imagenes = imagenesPrendaServicio.listarTodas();
        return ResponseEntity.ok(imagenes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagenesPrendaResponseDTO> actualizar(@PathVariable UUID id,
                                                                  @RequestBody ImagenesPrendaRequestDTO requestDTO) {
        ImagenesPrendaResponseDTO actualizada = imagenesPrendaServicio.actualizar(id, requestDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        imagenesPrendaServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}