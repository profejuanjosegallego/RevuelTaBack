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

    /**
     * Convierte el DTO en la entidad.
     *
     * Son DOS usuarios distintos: quien escribe la reseña (autor) y quien la
     * recibe (usuarioReseñado). El servicio los busca en la base de datos y los
     * entrega aqui ya resueltos.
     */
    public Reseña toEntity(Usuario autor, Usuario usuarioReseñado) {
        Reseña reseña = new Reseña();

        reseña.setComentario(comentario);
        reseña.setTitulo(titulo);
        reseña.setFecha(fecha);
        reseña.setRecomendado(recomendado);
        reseña.setEditada(editada != null ? editada : Boolean.FALSE);
        reseña.setVisible(visible != null ? visible : Boolean.TRUE);
        reseña.setAutor(autor);
        reseña.setUsuarioReseñado(usuarioReseñado);

        return reseña;
    }
}
