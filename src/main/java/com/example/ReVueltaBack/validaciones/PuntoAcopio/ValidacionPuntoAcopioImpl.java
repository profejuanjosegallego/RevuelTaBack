package com.example.ReVueltaBack.validaciones.PuntoAcopio;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import com.example.ReVueltaBack.modelos.PuntoAcopio;

@Component
public class ValidacionPuntoAcopioImpl implements IValidacionPuntoAcopio {

    private static final int LONGITUD_MINIMA_DIRECCION = 3;
    private static final int LONGITUD_MAXIMA_DIRECCION = 255;

    @Override
    public void validarNombreObligatorio(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }
    }

    @Override
    public void validarDireccionLongitud(String direccion) {
        if (direccion == null || direccion.length() < LONGITUD_MINIMA_DIRECCION || direccion.length() > LONGITUD_MAXIMA_DIRECCION) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                "La direccion es obligatoria y debe tener entre " + LONGITUD_MINIMA_DIRECCION + " y " + LONGITUD_MAXIMA_DIRECCION + " caracteres"
            );
        }
    }

    @Override
    public void validarCapacidadNoNegativo(Integer capacidad) {
        if (capacidad == null || capacidad < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La capacidad es obligatoria y no puede ser negativa");
        }
    }

    @Override
    public void validar(PuntoAcopio puntoAcopio) {
        if (puntoAcopio == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El objeto puntoAcopio no puede ser nulo");
        }
        validarNombreObligatorio(puntoAcopio.getNombre());
        validarDireccionLongitud(puntoAcopio.getDireccion());
        validarCapacidadNoNegativo(puntoAcopio.getCapacidad());
    }
}