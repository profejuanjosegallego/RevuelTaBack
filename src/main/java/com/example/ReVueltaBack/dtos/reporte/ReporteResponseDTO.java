package com.example.ReVueltaBack.dtos.reporte;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Reporte;

public record ReporteResponseDTO(
    UUID id,
    String motivo,
    String descripcion,
    String estado,
    String prioridad,
    LocalDate fecha,
    Boolean resuelto,
    UUID idReportante,
    UUID idPrenda
) {
    
    public static ReporteResponseDTO fromEntity(Reporte reporte) {
        
        return new ReporteResponseDTO(
        
            reporte.getId(),
            reporte.getMotivo(),
            reporte.getDescripcion(),
            reporte.getEstado(),
            reporte.getPrioridad(),
            reporte.getFecha(),
            reporte.getResuelto(),
            reporte.getUsuario().getId(),
            reporte.getPrenda().getId()
        
        );
    
    }

}
