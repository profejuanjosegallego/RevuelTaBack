package com.example.ReVueltaBack.dtos.calificacion;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Calificacion;
import com.example.ReVueltaBack.modelos.Resena;

public record CalificacionRequestDTO(
        Integer puntaje,
        String dimension,
        String comentarioCorto,
        LocalDate fecha,
        Boolean verificada,
        Integer peso,
        UUID idResena) {

    public Calificacion toEntity(Resena resena) {
        Calificacion calificacion = new Calificacion();

        calificacion.setPuntaje(puntaje);
        calificacion.setDimension(dimension);
        calificacion.setComentario_corto(comentarioCorto);
        calificacion.setFecha(fecha);
        calificacion.setVerificada(verificada);
        calificacion.setPeso(peso);
        calificacion.setResena(resena);

        return calificacion;
    }
}