package com.example.ReVueltaBack.servicios.trueque;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.trueque.TruequesRequestDTO;
import com.example.ReVueltaBack.dtos.trueque.TruequesResponseDTO;

public interface ITruequesServicio {

    TruequesResponseDTO crear(TruequesRequestDTO dto);

    List<TruequesResponseDTO> listar();

    TruequesResponseDTO buscarPorId(UUID id);

    TruequesResponseDTO actualizar(UUID id, TruequesRequestDTO dto);

    void eliminar(UUID id);
}