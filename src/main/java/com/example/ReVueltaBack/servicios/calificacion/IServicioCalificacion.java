package com.example.ReVueltaBack.servicios.calificacion;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.calificacion.CalificacionRequestDTO;
import com.example.ReVueltaBack.dtos.calificacion.CalificacionResponseDTO;

public interface IServicioCalificacion {

    CalificacionResponseDTO crear(CalificacionRequestDTO dto);
    List<CalificacionResponseDTO> listar();
    CalificacionResponseDTO buscarPorId(UUID id);
    CalificacionResponseDTO actualizar(UUID id, CalificacionRequestDTO dto);
    void eliminar(UUID id);
}