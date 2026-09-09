package com.example.ReVueltaBack.servicios.resena;

import com.example.ReVueltaBack.dtos.resena.ResenaDetalleResponseDTO;
import com.example.ReVueltaBack.dtos.resena.ResenaRequestDTO;
import com.example.ReVueltaBack.dtos.resena.ResenaResponseDTO;

import java.util.List;
import java.util.UUID;

public interface IServicioResena {
    ResenaResponseDTO crear(ResenaRequestDTO dto);
    List<ResenaResponseDTO> listar();
    List<ResenaDetalleResponseDTO> listarPorUsuarioResenado(UUID idUsuario);
    ResenaResponseDTO buscarPorId(UUID id);
    ResenaResponseDTO actualizar(UUID id, ResenaRequestDTO dto);
    void eliminar(UUID id);
}
