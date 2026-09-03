package com.example.ReVueltaBack.servicios.reporte;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.reporte.ReporteRequestDTO;
import com.example.ReVueltaBack.dtos.reporte.ReporteResponseDTO;

public interface IServicioReporte {

     ReporteResponseDTO crear(ReporteRequestDTO dto);
     List<ReporteResponseDTO> listar();
     List<ReporteResponseDTO> listarPorUsuarioReportado(UUID idUsuario);
     ReporteResponseDTO buscarPorId(UUID id);
     ReporteResponseDTO actualizar(UUID id, ReporteRequestDTO dto);
     void eliminar(UUID id);

}
