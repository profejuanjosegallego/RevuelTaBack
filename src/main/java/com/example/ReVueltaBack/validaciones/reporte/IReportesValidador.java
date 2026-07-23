package com.example.ReVueltaBack.validaciones.reporte;

import java.time.LocalDate;

import com.example.ReVueltaBack.modelos.Reporte;

public interface IReportesValidador {

    void validarMotivoObligatorio(String motivo);
    void validarDescripcionLongitud(String descripcion);
    void validarFechaNoFutura(LocalDate fecha);
    void validar(Reporte reportes);

}
