package com.example.ReVueltaBack.dtos.resena;

import java.util.UUID;
import com.example.ReVueltaBack.modelos.Resena;

public record ResenaDetalleResponseDTO(
    UUID id,
    String titulo,
    String comentario,
    AutorDTO autor,
    CalificacionDTO calificacion
) {
    public record AutorDTO(
        UUID id,
        String nombre,
        String color_avatar
    ) {}

    public record CalificacionDTO(
        Integer puntaje
    ) {}

    public static ResenaDetalleResponseDTO fromEntity(Resena resena) {
        AutorDTO autorDTO = null;
        if (resena.getAutor() != null) {
            autorDTO = new AutorDTO(
                resena.getAutor().getId(),
                resena.getAutor().getNombre(),
                resena.getAutor().getColor_avatar()
            );
        }

        CalificacionDTO calificacionDTO = null;
        if (resena.getCalificacion() != null) {
            calificacionDTO = new CalificacionDTO(
                resena.getCalificacion().getPuntaje()
            );
        }

        return new ResenaDetalleResponseDTO(
            resena.getId(),
            resena.getTitulo(),
            resena.getComentario(),
            autorDTO,
            calificacionDTO
        );
    }
}

