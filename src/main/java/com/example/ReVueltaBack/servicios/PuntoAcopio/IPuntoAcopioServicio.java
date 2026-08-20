package com.example.ReVueltaBack.servicios.PuntoAcopio;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.PuntoAcopio.PuntoAcopioRequestDTO;
import com.example.ReVueltaBack.dtos.PuntoAcopio.PuntoAcopioResponseDTO;

public interface IPuntoAcopioServicio {

    PuntoAcopioResponseDTO crear(PuntoAcopioRequestDTO dto);
    List<PuntoAcopioResponseDTO> listar();
    PuntoAcopioResponseDTO buscarPorId(UUID id);
    PuntoAcopioResponseDTO actualizar(UUID id, PuntoAcopioRequestDTO dto);
    void eliminar(UUID id);

}
