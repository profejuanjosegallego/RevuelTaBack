package com.example.ReVueltaBack.controladores;

import java.net.URI;
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

import com.example.ReVueltaBack.dtos.transaccion.TransaccionRequestDTO;
import com.example.ReVueltaBack.dtos.transaccion.TransaccionResponseDTO;
import com.example.ReVueltaBack.servicios.Transaccion.IServicioTransaccion;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionControlador {

    private final IServicioTransaccion servicioTransaccion;

    public TransaccionControlador(IServicioTransaccion servicioTransaccion) {
        this.servicioTransaccion = servicioTransaccion;
    }

    @PostMapping
    public ResponseEntity<TransaccionResponseDTO> crear(@RequestBody TransaccionRequestDTO datos) {
        TransaccionResponseDTO transaccionCreada = servicioTransaccion.crear(datos);
        return ResponseEntity.created(URI.create("/api/transacciones/" + transaccionCreada.id()))
                .body(transaccionCreada);
    }

    @GetMapping
    public ResponseEntity<List<TransaccionResponseDTO>> listar() {
        return ResponseEntity.ok(servicioTransaccion.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransaccionResponseDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(servicioTransaccion.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransaccionResponseDTO> actualizar(@PathVariable UUID id,
            @RequestBody TransaccionRequestDTO datos) {
        return ResponseEntity.ok(servicioTransaccion.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        servicioTransaccion.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}