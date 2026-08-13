package com.example.ReVueltaBack.dtos.Reseña;

import com.example.ReVueltaBack.modelos.Reseña;

import java.time.LocalDate;
import java.util.UUID;

public record ReseñaResponseDTO(
        UUID id,
        String comentario,
        String titulo,
        LocalDate fecha,
        Boolean recomendado,
        Boolean editada,
        Boolean visible,
        UUID idAutor,
        UUID idUsuarioReseñado
){
    public static ReseñaResponseDTO fromEntity(Reseña reseña){

        return new ReseñaResponseDTO(
                reseña.getId(),
                reseña.getComentario(),
                reseña.getTitulo(),
                reseña.getFecha(),
                reseña.getRecomendado(),
                reseña.getEditada(),
                reseña.getVisible(),
                reseña.getAutor().getId(),
                reseña.getUsuarioReseñado().getId()
        );
    }
}
