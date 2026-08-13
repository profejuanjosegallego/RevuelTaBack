package com.example.ReVueltaBack.servicios.calificaciones;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.calificaciones.CalificacionesRequestDTO;
import com.example.ReVueltaBack.dtos.calificaciones.CalificacionesResponseDTO;

public interface ICalificacionesServicio {

    CalificacionesResponseDTO crear(CalificacionesRequestDTO dto);
    List<CalificacionesResponseDTO> listar();
    CalificacionesResponseDTO buscarPorId(UUID id);
    CalificacionesResponseDTO actualizar(UUID id, CalificacionesRequestDTO dto);
    void eliminar(UUID id);
}