package com.example.ReVueltaBack.dtos.Reseña;

import com.example.ReVueltaBack.modelos.Reseña;
import com.example.ReVueltaBack.modelos.Usuario;

import java.time.LocalDate;
import java.util.UUID;

public record ReseñaRequestDTO(
    String comentario,
    String titulo,
    LocalDate fecha,
    Boolean recomendado,
    Boolean editada,
    Boolean visible,
    UUID idAutor,
    UUID idUsuarioReseñado
) {

    public Reseña toEntity() {
        Reseña reseña = new Reseña();
        Usuario usuario = new Usuario();

        usuario.setId(idAutor);
        usuario.setId(idUsuarioReseñado);

        reseña.setComentario(comentario);
        reseña.setTitulo(titulo);
        reseña.setFecha(fecha);
        reseña.setRecomendado(recomendado);
        reseña.setEditada(editada);
        reseña.setVisible(visible);
        return reseña;
    }
}
