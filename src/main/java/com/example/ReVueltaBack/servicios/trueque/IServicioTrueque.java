package com.example.ReVueltaBack.servicios.trueque;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.trueque.TruequeRequestDTO;
import com.example.ReVueltaBack.dtos.trueque.TruequeResponseDTO;

public interface IServicioTrueque {

    TruequeResponseDTO crear(TruequeRequestDTO dto);

    List<TruequeResponseDTO> listar();

    TruequeResponseDTO buscarPorId(UUID id);

    TruequeResponseDTO actualizar(UUID id, TruequeRequestDTO dto);

    void eliminar(UUID id);
}