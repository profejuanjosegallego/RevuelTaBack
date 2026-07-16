package com.example.ReVueltaBack.validaciones.reporte;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.Reporte;

@Component
public class ReportesValidadorImpl implements IReportesValidador {

    private static final int MAX_DESCRIPCION_LENGTH = 255;
    private static final int MIN_DESCRIPCION_LENGTH = 3;
    private LocalDate fechaActual = LocalDate.now();

    @Override
    public void validarMotivoObligatorio(String motivo) {
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El motivo es obligatorio.");
        }
    }

    @Override
    public void validarDescripcionLongitud(String descripcion) {
        if (descripcion == null || descripcion.length() < MIN_DESCRIPCION_LENGTH || descripcion.length() > MAX_DESCRIPCION_LENGTH) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La descripción debe tener entre 3 y 255 caracteres.");
            
        }
    }

    @Override
    public void validarFechaNoFutura(LocalDate fecha) {
        if (fecha.isAfter(fechaActual)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha no puede ser futura.");
        }
    }

    @Override
    public void validar(Reporte reportes) {
        validarMotivoObligatorio(reportes.getMotivo());
        validarDescripcionLongitud(reportes.getDescripcion());
        validarFechaNoFutura(reportes.getFecha().toLocalDate());
    }

}
