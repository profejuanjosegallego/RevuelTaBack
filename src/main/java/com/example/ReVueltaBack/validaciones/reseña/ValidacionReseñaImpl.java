package com.example.ReVueltaBack.validaciones.reseña;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.Reseña;

@Component
public class ValidacionReseñaImpl implements IValidacionReseña{

    private static final int LONGITUD_COMENTARIO_MIN = 3;

    private static final int LONGITUD_COMENTARIO_MAX = 255;

    private LocalDate fechaActual = LocalDate.now();

    @Override
    public void validarTituloObligatorio(String titulo) {
        if(titulo == null || titulo.trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El comentario no puede estar en blanco.");
        }
    }

    @Override
    public void validarComentarioLongitud(String comentario) {
        if (comentario.length() < LONGITUD_COMENTARIO_MIN || comentario.length() > LONGITUD_COMENTARIO_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El comentario debe estar entre 3 y 255 caracteres,");
        
        }
    }

    @Override
    public void validarFechaNoFutura(LocalDate fecha) {
        if(fecha.isAfter(fechaActual)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha no puede ser futura");
        }
    }

    @Override
    public void validar(Reseña reseñas) {
    validarTituloObligatorio(reseñas.getTitulo());
    validarComentarioLongitud(reseñas.getComentario());
    validarFechaNoFutura(reseñas.getFecha());
    }



}