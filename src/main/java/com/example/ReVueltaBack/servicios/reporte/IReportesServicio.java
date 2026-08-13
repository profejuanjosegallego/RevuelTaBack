package com.example.ReVueltaBack.servicios.reporte;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.reporte.ReportesRequestDTO;
import com.example.ReVueltaBack.dtos.reporte.ReportesResponseDTO;

public interface IReportesServicio {

     ReportesResponseDTO crear(ReportesRequestDTO dto);
     List<ReportesResponseDTO> listar();
     ReportesResponseDTO buscarPorId(UUID id);
     ReportesResponseDTO actualizar(UUID id, ReportesRequestDTO dto);
     void eliminar(UUID id);

}
