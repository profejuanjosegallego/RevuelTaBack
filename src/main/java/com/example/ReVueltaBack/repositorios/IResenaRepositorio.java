package com.example.ReVueltaBack.repositorios;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Resena;
import com.example.ReVueltaBack.modelos.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IResenaRepositorio extends JpaRepository<Resena, UUID> {


    List<Resena> findByComentario(String comentario);
    List<Resena> findByTituloContainingIgnoreCase(String titulo);
    List<Resena> findByFecha(LocalDate fecha);
    List<Resena> findByRecomendado(Boolean recomendado);
    List<Resena> findByEditada(Boolean editada);
    List<Resena> findByVisible(Boolean visible);
    List<Resena> findByAutor(Usuario autor);
    List<Resena> findByUsuarioResenado(Usuario usuarioResenado);

    @Query("SELECT r FROM Resena r WHERE r.autor.id = :autorId")
    List<Resena> buscarPorUsuariosId(@Param("autorId") UUID autorId);

    @Query("SELECT r FROM Resena r JOIN r.usuarioResenado u WHERE u.id = :idUsuario")
    List<Resena> buscarPorUsuarioResenado(@Param("idUsuario") UUID idUsuario);
}