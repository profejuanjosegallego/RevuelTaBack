package com.example.ReVueltaBack.servicios.recompensa;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.recompensa.RecompensaRequestDTO;
import com.example.ReVueltaBack.dtos.recompensa.RecompensaResponseDTO;

public interface IServicioRecompensa {

    RecompensaResponseDTO crear(RecompensaRequestDTO dto);

    List<RecompensaResponseDTO> listar();

    RecompensaResponseDTO buscarPorId(UUID id);

RecompensaResponseDTO actualizar(UUID id, RecompensaRequestDTO dto);

void eliminar(UUID id);
}
