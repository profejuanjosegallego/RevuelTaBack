package com.example.ReVueltaBack.validaciones.recompensa;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.Recompensa;

@Component
public class ValidacionRecompensaImpl implements IValidacionRecompensa {

    private static final List<String> TIPOS_PERMITIDOS = List.of(
        "DESCUENTO", 
        "CUPON", 
        "PRODUCTO_FISICO", 
        "ENVIO_GRATIS", 
        "EXPERIENCIA"
    );

    @Override
    public void validarNombreObligatorio(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                "El nombre de la recompensa es obligatorio y no puede estar vacío."
            );
        }
    }

    @Override
    public void validarDescripcionLongitud(String descripcion) {
        if (descripcion != null) {
            int longitud = descripcion.trim().length();
            if (longitud < 3 || longitud > 255) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, 
                    "La descripción debe tener entre 3 y 255 caracteres."
                );
            }
        }
    }

    @Override
    public void validarTipoPermitido(String tipo) {
        if (tipo == null || !TIPOS_PERMITIDOS.contains(tipo.toUpperCase().trim())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                "El tipo de recompensa no es válido. Tipos permitidos: " + TIPOS_PERMITIDOS
            );
        }
    }

    @Override
    public void validar(Recompensa recompensa) {
        if (recompensa == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                "La entidad recompensa a validar no puede ser nula."
            );
        }

        validarNombreObligatorio(recompensa.getNombre());
        validarDescripcionLongitud(recompensa.getDescripcion());
        validarTipoPermitido(recompensa.getTipo());
    }
}
