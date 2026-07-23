package com.example.ReVueltaBack.validaciones.envio;

import java.time.LocalDate;

import com.example.ReVueltaBack.modelos.Envio;

public interface IValidacionEnvio {

    void validarCodigoGuiaObligatorio (String codigoGuia);

    void validarCostoPositivo (Double costo);

    void validarFechaDespachoNoFutura (LocalDate fechaDespacho);

    void validarEnvio (Envio envio);
    
}
