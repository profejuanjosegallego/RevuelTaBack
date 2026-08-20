package com.example.ReVueltaBack.servicios.Usuario;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.Usuario.UsuarioRequestDTO;
import com.example.ReVueltaBack.dtos.Usuario.UsuarioResponseDTO;

// HU USR-S01 — Contrato del servicio de Usuario (modulo comun de Autenticacion).
// El controlador SOLO conoce esta interfaz, nunca la implementacion.
public interface IServicioUsuario {

    UsuarioResponseDTO registrar(UsuarioRequestDTO datos);

    List<UsuarioResponseDTO> listar();

    UsuarioResponseDTO buscarPorId(UUID id);

    UsuarioResponseDTO buscarPorCorreo(String correo);

    UsuarioResponseDTO actualizar(UUID id, UsuarioRequestDTO datos);

    // La contrasena NO se cambia con actualizar(): tiene su propia operacion
    // porque hay que volver a cifrarla.
    UsuarioResponseDTO cambiarContrasena(UUID id, String contrasenaNueva);

    void eliminar(UUID id);
}
