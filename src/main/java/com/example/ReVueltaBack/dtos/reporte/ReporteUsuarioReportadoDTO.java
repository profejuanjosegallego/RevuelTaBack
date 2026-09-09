package com.example.ReVueltaBack.dtos.reporte;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Reporte;

public record ReporteUsuarioReportadoDTO(
    UUID id,
    String motivo,
    String descripcion,
    String estado,
    String prioridad,
    LocalDate fecha
) {

    public static ReporteUsuarioReportadoDTO fromEntity(Reporte reporte) {
        return new ReporteUsuarioReportadoDTO(
            reporte.getId(),
            reporte.getMotivo(),
            reporte.getDescripcion(),
            reporte.getEstado(),
            reporte.getPrioridad(),
            reporte.getFecha()
        );
    }
}
