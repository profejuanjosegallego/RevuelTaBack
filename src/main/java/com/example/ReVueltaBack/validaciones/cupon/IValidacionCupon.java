package com.example.ReVueltaBack.validaciones.cupon;

import java.time.LocalDateTime;

import com.example.ReVueltaBack.modelos.Cupon;

public interface IValidacionCupon {

    void validarCodigoObligatorio(String codigo);

    void validarTipoPermitido(String tipo);

    void validarValor(String tipo, String valor);

    void validarUsos(Integer usosMaximos, Integer usosActuales);

    void validarFechaExpiracion(LocalDateTime fechaExpiracion);

    void validarCampanaAsociada(Cupon cupon);

    void validarCupon(Cupon cupon);
}
