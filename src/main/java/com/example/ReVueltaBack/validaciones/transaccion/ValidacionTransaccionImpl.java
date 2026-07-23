package com.example.ReVueltaBack.validaciones.transaccion;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.Transaccion;

// Criterio: "La clase ValidacionTransaccionImpl implementa IValidacionTransaccion,
// está anotada con @Component..."
@Component
public class ValidacionTransaccionImpl implements IValidacionTransaccion {

    // Longitud mínima y máxima permitida para el campo "comprobante"
    // (usadas en la Validación 2)
    private static final int LONGITUD_MIN_COMPROBANTE = 3;
    private static final int LONGITUD_MAX_COMPROBANTE = 255;

    
    // VALIDACIÓN 1
    // "void validarReferenciaPagoObligatorio(String referenciaPago)":
    // Obligatoriedad: referencia_pago no puede ser null ni una cadena
    // vacía o en blanco.
    
    @Override
    public void validarReferenciaPagoObligatorio(String referenciaPago) {
        if (referenciaPago == null || referenciaPago.isBlank()) {
            // "...cada regla lanza ResponseStatusException(HttpStatus.BAD_REQUEST, ...)
            // con un mensaje claro cuando el dato es inválido (Spring responde 400 Bad Request)"
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "La referencia de pago es obligatoria");
        }
    }

    
    // VALIDACIÓN 2
    // "void validarComprobanteLongitud(String comprobante)":
    // Tamaño: la longitud de comprobante debe estar entre 3 y 255 caracteres.
    
    @Override
    public void validarComprobanteLongitud(String comprobante) {
        if (comprobante == null
                || comprobante.length() < LONGITUD_MIN_COMPROBANTE
                || comprobante.length() > LONGITUD_MAX_COMPROBANTE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "El comprobante debe tener entre 3 y 255 caracteres");
        }
    }

    
    // VALIDACIÓN 3
    // "void validarMontoPositivo(Double monto)":
    // Rango: monto debe ser mayor que 0 (sin negativos ni cero).
    
    @Override
    public void validarMontoPositivo(Double monto) {
        if (monto == null || monto <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "El monto debe ser mayor que 0");
        }
    }

    
    // MÉTODO UNIFICADOR
    // "void validar(Transaccion transaccion) que invoca las 3 validaciones
    // y se detiene en la primera regla que falle."
    // Como cada validación individual lanza la excepción de inmediato,
    // si validarReferenciaPagoObligatorio() falla, la ejecución nunca
    // llega a las siguientes dos líneas (comportamiento "fail-fast").
    
    @Override
    public void validar(Transaccion transaccion) {
        validarReferenciaPagoObligatorio(transaccion.getReferencia_pago());
        validarComprobanteLongitud(transaccion.getComprobante());
        validarMontoPositivo(transaccion.getMonto());
    }

    
}