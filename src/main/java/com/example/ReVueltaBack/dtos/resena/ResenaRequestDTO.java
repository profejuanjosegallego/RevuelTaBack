package com.example.ReVueltaBack.dtos.resena;

import com.example.ReVueltaBack.modelos.Resena;
import com.example.ReVueltaBack.modelos.Usuario;

import java.time.LocalDate;
import java.util.UUID;

public record ResenaRequestDTO(
    String comentario,
    String titulo,
    LocalDate fecha,
    Boolean recomendado,
    Boolean editada,
    Boolean visible,
    UUID idAutor,
    UUID idUsuarioResenado
) {

    /**
     * Convierte el DTO en la entidad.
     *
     * Son DOS usuarios distintos: quien escribe la reseña (autor) y quien la
     * recibe (usuarioReseñado). El servicio los busca en la base de datos y los
     * entrega aqui ya resueltos.
     */
    public Resena toEntity(Usuario autor, Usuario usuarioResenado) {
        Resena resena = new Resena();

        resena.setComentario(comentario);
        resena.setTitulo(titulo);
        resena.setFecha(fecha);
        resena.setRecomendado(recomendado);
        resena.setEditada(editada != null ? editada : Boolean.FALSE);
        resena.setVisible(visible != null ? visible : Boolean.TRUE);
        resena.setAutor(autor);
        resena.setUsuarioResenado(usuarioResenado);

        return resena;
    }
}
