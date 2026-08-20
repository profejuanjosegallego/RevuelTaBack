package com.example.ReVueltaBack.dtos.resena;

import com.example.ReVueltaBack.modelos.Resena;

import java.time.LocalDate;
import java.util.UUID;

public record ResenaResponseDTO(
        UUID id,
        String comentario,
        String titulo,
        LocalDate fecha,
        Boolean recomendado,
        Boolean editada,
        Boolean visible,
        UUID idAutor,
        UUID idUsuarioResenado
){
    public static ResenaResponseDTO fromEntity(Resena resena){

        return new ResenaResponseDTO(
                resena.getId(),
                resena.getComentario(),
                resena.getTitulo(),
                resena.getFecha(),
                resena.getRecomendado(),
                resena.getEditada(),
                resena.getVisible(),
                resena.getAutor().getId(),
                resena.getUsuarioResenado().getId()
        );
    }
}
