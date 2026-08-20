package com.example.ReVueltaBack.validaciones.prenda;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.Prenda;

@Component
public class ValidacionPrendaImpl implements IValidacionPrenda {

    private static final int LONGITUD_MINIMA = 3;
    private static final int LONGITUD_MAXIMA = 255;
    private static final double PRECIO_MINIMO = 0.0;

    @Override
    public void validarTituloObligatorio(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El titulo de la  prenda es obligatorio");
        }
    }

    @Override
    public void validarDescripcionLongitud(String descripcion) {
        if (descripcion == null || descripcion.length() < LONGITUD_MINIMA || descripcion.length() > LONGITUD_MAXIMA) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "la descripcion debe tener entre" + LONGITUD_MINIMA + " y " + LONGITUD_MAXIMA + " de caracteres");
        }
    }

    @Override
    public void validarPrecioPositivo(Double precio) {
        if (precio == null || precio <= PRECIO_MINIMO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El precio debe ser mayor que " + PRECIO_MINIMO);
        }
    }

    @Override
    public void validar(Prenda prendas) {
        validarTituloObligatorio(prendas.getTitulo());
        validarDescripcionLongitud(prendas.getDescripcion());
        validarPrecioPositivo(prendas.getPrecio());
    }

}
