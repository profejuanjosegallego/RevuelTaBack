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
import org.springframework.web.service.annotation.PatchExchange;

import com.example.ReVueltaBack.dtos.Usuario.UsuarioRequestDTO;
import com.example.ReVueltaBack.dtos.Usuario.UsuarioResponseDTO;
import com.example.ReVueltaBack.servicios.Usuario.IServicioUsuario;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {


    private final IServicioUsuario servicioUsuario;

    public UsuarioControlador(IServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }


    @PostMapping
    public ResponseEntity<UsuarioResponseDTO>registrar(@RequestBody UsuarioRequestDTO datos){
        UsuarioResponseDTO usuarioCreado=servicioUsuario.registrar(datos);
        return ResponseEntity.ok(usuarioCreado);
    }
    
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>>listar(){
        return ResponseEntity.ok(servicioUsuario.listar());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO>buscarPorId(@PathVariable UUID id){
        return ResponseEntity.ok(servicioUsuario.buscarPorId(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO>actualizar(@RequestBody UsuarioRequestDTO datos, @PathVariable UUID id){
        return ResponseEntity.ok(servicioUsuario.actualizar(id, datos));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable UUID id){
        servicioUsuario.eliminar(id);
        return ResponseEntity.noContent().build();

    }
    



}
