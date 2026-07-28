package com.example.ReVueltaBack.dtos.reporte;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Prenda;
import com.example.ReVueltaBack.modelos.Reporte;
import com.example.ReVueltaBack.modelos.Usuario;

public record ReportesRequestDTO(
    String motivo,
    String descripcion,
    String estado,
    String prioridad,
    LocalDate fecha,
    Boolean resuelto,
    UUID idReportante,
    UUID idPrenda
) {

    public Reporte toEntity() {
        Reporte reporte = new Reporte();
        Prenda prenda = new Prenda();
        Usuario usuario = new Usuario();

        prenda.setId(idPrenda);
        usuario.setId(idReportante);

        reporte.setMotivo(motivo);
        reporte.setDescripcion(descripcion);
        reporte.setEstado(estado);
        reporte.setPrioridad(prioridad);
        reporte.setFecha(fecha);
        reporte.setResuelto(resuelto);
        reporte.setUsuario(usuario);
        reporte.setPrenda(prenda);
        return reporte;
    }
}
