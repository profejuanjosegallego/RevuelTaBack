package com.example.ReVueltaBack.dtos.calificaciones;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Calificacion;
import com.example.ReVueltaBack.modelos.Reseña;

public record CalificacionesRequestDTO(
        Integer puntaje,
        String dimension,
        String comentarioCorto,
        LocalDate fecha,
        Boolean verificada,
        Integer peso,
        UUID reseñaID) {

    public Calificacion toEntity(Reseña reseña) {
        Calificacion calificacion = new Calificacion();

        calificacion.setPuntaje(puntaje);
        calificacion.setDimension(dimension);
        calificacion.setComentario_corto(comentarioCorto);
        calificacion.setFecha(fecha);
        calificacion.setVerificada(verificada);
        calificacion.setPeso(peso);
        calificacion.setReseña(reseña);

        return calificacion;
    }
}