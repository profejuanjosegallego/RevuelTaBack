package com.example.ReVueltaBack.controladores.trueque;

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

import com.example.ReVueltaBack.dtos.trueque.TruequesRequestDTO;
import com.example.ReVueltaBack.dtos.trueque.TruequesResponseDTO;
import com.example.ReVueltaBack.servicios.trueque.ITruequesServicio;

/**
 * Expone Trueques por HTTP.
 *
 * Solo recibe la peticion, delega en el servicio y devuelve la respuesta
 * con su codigo HTTP. No contiene logica de negocio ni habla con el
 * repositorio directamente.
 */
@RestController
@RequestMapping("/api/trueques")
public class TruequesControlador {

    private final ITruequesServicio truequesServicio;

    public TruequesControlador(ITruequesServicio truequesServicio) {
        this.truequesServicio = truequesServicio;
    }

    @PostMapping
    public ResponseEntity<TruequesResponseDTO> crear(@RequestBody TruequesRequestDTO dto) {
        TruequesResponseDTO creado = truequesServicio.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<TruequesResponseDTO>> listar() {
        List<TruequesResponseDTO> trueques = truequesServicio.listar();
        return ResponseEntity.ok(trueques);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TruequesResponseDTO> buscarPorId(@PathVariable UUID id) {
        TruequesResponseDTO trueque = truequesServicio.buscarPorId(id);
        return ResponseEntity.ok(trueque);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TruequesResponseDTO> actualizar(@PathVariable UUID id,
                                                            @RequestBody TruequesRequestDTO dto) {
        TruequesResponseDTO actualizado = truequesServicio.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        truequesServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}