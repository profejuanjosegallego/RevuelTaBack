package com.example.ReVueltaBack.repositorios;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Reseña;
import com.example.ReVueltaBack.modelos.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IReseñaRepositorio extends JpaRepository<Reseña, UUID> {


    List<Reseña> findByComentario(String comentario);
    List<Reseña> findByTituloContainingIgnoreCase(String titulo);
    List<Reseña> findByFecha(LocalDate fecha);
    List<Reseña> findByRecomendado(Boolean recomendado);
    List<Reseña> findByEditada(Boolean editada);
    List<Reseña> findByVisible(Boolean visible);
    List<Reseña> findByAutor(Usuario autor);
    List<Reseña> findByUsuarioReseñado(Usuario usuarioReseñado);

    @Query("SELECT r FROM Reseña r WHERE r.autor.id = :autorId")
    List<Reseña> buscarPorUsuariosId(@Param("autorId") UUID autorId);
}