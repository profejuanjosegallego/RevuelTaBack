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
import org.springframework.web.bind.annotation.RestController;

import com.example.ReVueltaBack.dtos.seguimientoenvio.SeguimientoEnvioRequestDTO;
import com.example.ReVueltaBack.dtos.seguimientoenvio.SeguimientoEnvioResponseDTO;
import com.example.ReVueltaBack.servicios.seguimientoenvio.ISeguimientoEnvioServicio;

@RestController
@RequestMapping("/api/seguimiento-envio")
public class SeguimientoEnvioControlador {

    private final ISeguimientoEnvioServicio servicioSeguimientoEnvio;

    public SeguimientoEnvioControlador(ISeguimientoEnvioServicio servicioSeguimientoEnvio) {
        this.servicioSeguimientoEnvio = servicioSeguimientoEnvio;
    }

    @PostMapping
    public ResponseEntity<SeguimientoEnvioResponseDTO> crear(@RequestBody SeguimientoEnvioRequestDTO dto) {
        SeguimientoEnvioResponseDTO seguimientoCreado = servicioSeguimientoEnvio.crear(dto);
        return ResponseEntity.created(null).body(seguimientoCreado);
    }

    @GetMapping
    public ResponseEntity<List<SeguimientoEnvioResponseDTO>> listar() {
        return ResponseEntity.ok(servicioSeguimientoEnvio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeguimientoEnvioResponseDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(servicioSeguimientoEnvio.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeguimientoEnvioResponseDTO> actualizar(@PathVariable UUID id, @RequestBody SeguimientoEnvioRequestDTO dto) {
        return ResponseEntity.ok(servicioSeguimientoEnvio.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable UUID id){
        servicioSeguimientoEnvio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
