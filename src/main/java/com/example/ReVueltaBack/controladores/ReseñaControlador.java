package com.example.ReVueltaBack.controladores;

import com.example.ReVueltaBack.dtos.Reseña.ReseñaRequestDTO;
import com.example.ReVueltaBack.dtos.Reseña.ReseñaResponseDTO;
import com.example.ReVueltaBack.servicios.Reseña.IReseñaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reseñas")
public class ReseñaControlador {

    private final IReseñaServicio servicioReseña;

    public ReseñaControlador(IReseñaServicio servicioReseña) {
        this.servicioReseña = servicioReseña;
    }


    @PostMapping
    public ResponseEntity<ReseñaResponseDTO> crear(@RequestBody ReseñaRequestDTO dto){
        ReseñaResponseDTO reseñaCreada=servicioReseña.crear(dto);
        return ResponseEntity.ok(reseñaCreada);
    }

    @GetMapping
    public ResponseEntity<List<ReseñaResponseDTO>>listar(){
        return ResponseEntity.ok(servicioReseña.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReseñaResponseDTO>buscarPorId(@PathVariable UUID id){
        return ResponseEntity.ok(servicioReseña.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReseñaResponseDTO>actualizar(@RequestBody ReseñaRequestDTO datos, @PathVariable UUID id){
        return ResponseEntity.ok(servicioReseña.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable UUID id){
        servicioReseña.eliminar(id);
        return ResponseEntity.noContent().build();

    }
}
