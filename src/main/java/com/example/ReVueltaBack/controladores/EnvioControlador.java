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

import com.example.ReVueltaBack.dtos.envio.EnvioRequestDTO;
import com.example.ReVueltaBack.dtos.envio.EnvioResponseDTO;
import com.example.ReVueltaBack.servicios.envio.IServicioEnvio;


@RestController
@RequestMapping("/api/envios")
public class EnvioControlador {

    private final IServicioEnvio servicioEnvio;

    public EnvioControlador(IServicioEnvio servicioEnvio) {
        this.servicioEnvio = servicioEnvio;
    }

    @PostMapping
    public ResponseEntity<EnvioResponseDTO>crear(@RequestBody EnvioRequestDTO datos) {
        EnvioResponseDTO envioCreado = servicioEnvio.crear(datos);
        return ResponseEntity.ok(envioCreado);
    }

    @GetMapping
    public ResponseEntity<List<EnvioResponseDTO>>listar(){
        return ResponseEntity.ok(servicioEnvio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvioResponseDTO>buscarPorId(@PathVariable UUID id){
        return ResponseEntity.ok(servicioEnvio.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnvioResponseDTO>actualizar(@RequestBody EnvioRequestDTO datos, @PathVariable UUID id){
        return ResponseEntity.ok(servicioEnvio.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable UUID id){
        servicioEnvio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
