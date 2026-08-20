package com.example.ReVueltaBack.validaciones.estadoprenda;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.EstadoPrenda;

@Component
public class ValidacionEstadoPrendaImpl implements IValidacionEstadoPrenda {

    private static final int DESCRIPCION_MIN = 3;
    private static final int DESCRIPCION_MAX = 255;

    private static final int NIVEL_DESGASTE_MIN = 0;
    private static final int NIVEL_DESGASTE_MAX = 5;

    @Override
    public void validarNombreObligatorio(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del estado es obligatorio");
        }
    }

    @Override
    public void validarDescripcionLongitud(String descripcion) {
        if (descripcion == null || descripcion.length() < DESCRIPCION_MIN || descripcion.length() > DESCRIPCION_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La descripción debe tener entre 3 y 255 caracteres");
        }
    }

    @Override
    public void validarNivelDesgasteRango(Integer nivelDesgaste) {
        if (nivelDesgaste == null || nivelDesgaste < NIVEL_DESGASTE_MIN || nivelDesgaste > NIVEL_DESGASTE_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nivel de desgaste debe estar entre 0 y 5");
        }
    }

    @Override
    public void validar(EstadoPrenda estadoPrenda) {
        validarNombreObligatorio(estadoPrenda.getNombre());
        validarDescripcionLongitud(estadoPrenda.getDescripcion());
        validarNivelDesgasteRango(estadoPrenda.getNivelDesgaste());
    }

}
