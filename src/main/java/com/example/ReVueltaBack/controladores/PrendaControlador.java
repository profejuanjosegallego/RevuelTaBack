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

import com.example.ReVueltaBack.dtos.prenda.PrendaRequestDTO;
import com.example.ReVueltaBack.dtos.prenda.PrendaResponseDTO;
import com.example.ReVueltaBack.servicios.prenda.IServicioPrenda;

@RestController
@RequestMapping("/api/prendas")
public class PrendaControlador {
    private final IServicioPrenda servicioPrenda;

    public PrendaControlador(IServicioPrenda servicioPrenda) {
        this.servicioPrenda = servicioPrenda;
    }

    @PostMapping
    public ResponseEntity<PrendaResponseDTO>crear(@RequestBody PrendaRequestDTO dto){
        PrendaResponseDTO respuesta = servicioPrenda.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
    @GetMapping
    public ResponseEntity<List<PrendaResponseDTO>> listar(){
        return ResponseEntity.ok(servicioPrenda.listar());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PrendaResponseDTO> buscarPorId(@PathVariable UUID id){
        return ResponseEntity.ok(servicioPrenda.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrendaResponseDTO> actualizar(
        @PathVariable UUID id,
        @RequestBody PrendaRequestDTO dto){
    return ResponseEntity.ok(servicioPrenda.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id){
        servicioPrenda.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    
}
