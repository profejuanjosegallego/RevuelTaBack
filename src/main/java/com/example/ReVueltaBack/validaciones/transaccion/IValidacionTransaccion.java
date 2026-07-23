package com.example.ReVueltaBack.validaciones.transaccion;

import com.example.ReVueltaBack.modelos.Transaccion;

public interface IValidacionTransaccion {

    // Obligatoriedad: referencia_pago no puede ser null ni una cadena vacía o en blanco
    void validarReferenciaPagoObligatorio(String referenciaPago);

    // Tamaño: comprobante debe tener entre 3 y 255 caracteres
    void validarComprobanteLongitud(String comprobante);

    // Rango: monto debe ser mayor que 0 (sin negativos ni cero)
    void validarMontoPositivo(Double monto);

    // Unifica las 3 validaciones, se detiene en la primera regla que falle
    void validar(Transaccion transaccion);

    

}
