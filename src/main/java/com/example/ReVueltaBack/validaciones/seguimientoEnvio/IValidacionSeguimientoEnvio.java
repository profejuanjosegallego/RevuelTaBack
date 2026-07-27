package com.example.ReVueltaBack.validaciones.seguimientoenvio;

import java.time.LocalDateTime;

import com.example.ReVueltaBack.modelos.SeguimientoEnvio;

public interface IValidacionSeguimientoEnvio {

    void validarDescriptionObligarorio(String descripcion);

    void validarUbicacionLongitud(String ubicacion);

    void validarFechaHoraNoFutura(LocalDateTime fechaHora);

    void validar(SeguimientoEnvio seguimientoEnvio);

}
