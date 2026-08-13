package com.example.ReVueltaBack.servicios.Reseña;

import com.example.ReVueltaBack.dtos.Reseña.ReseñaRequestDTO;
import com.example.ReVueltaBack.dtos.Reseña.ReseñaResponseDTO;

import java.util.List;
import java.util.UUID;

public interface IReseñaServicio {
    ReseñaResponseDTO crear(ReseñaRequestDTO dto);
    List<ReseñaResponseDTO> listar();
    ReseñaResponseDTO buscarPorId(UUID id);
    ReseñaResponseDTO actualizar(UUID id, ReseñaRequestDTO dto);
    void eliminar(UUID id);
}
