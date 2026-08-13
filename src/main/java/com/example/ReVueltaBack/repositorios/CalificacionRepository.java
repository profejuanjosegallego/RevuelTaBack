package com.example.ReVueltaBack.repositorios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.Calificacion;
import com.example.ReVueltaBack.modelos.Reseña;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, UUID> {

    List<Calificacion> findByReseña(Reseña reseña);

    List<Calificacion> findByVerificadaTrue();

    List<Calificacion> findAllByOrderByFechaDesc();

    List<Calificacion> findByPuntajeGreaterThanEqual(Integer puntajeMinimo);

    @Query("SELECT c FROM Calificacion c WHERE c.reseña.id = :reseñaId")
    List<Calificacion> buscarPorReseñasId(@Param("reseñaId") UUID reseñaId);

}