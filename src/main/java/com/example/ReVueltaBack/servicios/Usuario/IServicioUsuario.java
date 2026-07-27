package com.example.ReVueltaBack.servicios.Usuario;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.Usuario.UsuarioRequestDTO;
import com.example.ReVueltaBack.dtos.Usuario.UsuarioResponseDTO;

public interface IServicioUsuario {

    UsuarioResponseDTO registrar(UsuarioRequestDTO datos);
    List<UsuarioResponseDTO> listar();
    UsuarioResponseDTO buscarPorId(UUID id);
    UsuarioResponseDTO actualizar(UUID id, UsuarioRequestDTO datos);
    void eliminar(UUID id);

}
