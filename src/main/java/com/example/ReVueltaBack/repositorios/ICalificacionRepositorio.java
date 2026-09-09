package com.example.ReVueltaBack.repositorios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.Calificacion;
import com.example.ReVueltaBack.modelos.Resena;

@Repository
public interface ICalificacionRepositorio extends JpaRepository<Calificacion, UUID> {

    List<Calificacion> findByResena(Resena resena);

    List<Calificacion> findByVerificadaTrue();

    List<Calificacion> findAllByOrderByFechaDesc();

    List<Calificacion> findByPuntajeGreaterThanEqual(Integer puntajeMinimo);

    @Query("SELECT c FROM Calificacion c WHERE c.resena.id = :resenaId")
    List<Calificacion> buscarPorResenasId(@Param("reseñaId") UUID resenaId);

    @Query("SELECT c FROM Calificacion c JOIN c.resena r WHERE r.usuarioResenado.id = :idUsuario")
    List<Calificacion> buscarCalificacionesPorUsuario(@Param("idUsuario") UUID idUsuario);
}