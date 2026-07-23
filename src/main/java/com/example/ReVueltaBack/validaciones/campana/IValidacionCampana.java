package com.example.ReVueltaBack.validaciones.campana;

import java.time.LocalDateTime;

import com.example.ReVueltaBack.modelos.Campana;

public interface IValidacionCampana {

    void validarNombreObligatorio(String nombre);

    void validarDescripcionObligatoria(String descripcion);

    void validarFechas(LocalDateTime fechaInicio, LocalDateTime fechaFinal);

    void validarDescuento(Double descuentoPct);

    void validarActiva(Boolean activa);

    void validarCampana(Campana campana);
}
