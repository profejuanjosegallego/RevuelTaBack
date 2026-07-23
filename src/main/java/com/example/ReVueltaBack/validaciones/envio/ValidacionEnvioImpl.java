package com.example.ReVueltaBack.validaciones.envio;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.Envio;

@Component
public class ValidacionEnvioImpl implements IValidacionEnvio {

    LocalDate fechaActual = LocalDate.now();

    @Override
    public void validarCodigoGuiaObligatorio (String codigoGuia){
        if (codigoGuia == null || codigoGuia.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El código guía no puede estar vacío");            
        }
    }

    @Override
    public void validarCostoPositivo (Double costo){
        if (costo < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El número debe ser mayor que 0");
        }
    }

    @Override
    public void validarFechaDespachoNoFutura (LocalDate fechaDespacho){
        if (fechaDespacho.isAfter(fechaActual)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de despacho no puede ser una fecha futura");
        }
    }

    @Override
    public void validarEnvio (Envio envio){
        validarCodigoGuiaObligatorio(envio.getCodigo_guia());
        validarCostoPositivo(envio.getCosto());
        validarFechaDespachoNoFutura(envio.getFecha_despacho());
    }

}
