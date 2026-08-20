package com.example.ReVueltaBack.repositorios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.Recompensa;

@Repository
public interface IRecompensaRepositorio extends JpaRepository<Recompensa, UUID> {

    @Query("SELECT r FROM Recompensa r WHERE LOWER(r.nombre) = LOWER(:nombre)")
    List<Recompensa> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT r FROM Recompensa r WHERE LOWER(r.descripcion) = LOWER(:descripcion)")
    List<Recompensa> buscarPorDescripcion(@Param("descripcion") String descripcion);

    @Query("SELECT r FROM Recompensa r WHERE LOWER(r.tipo) = LOWER(:tipo)")
    List<Recompensa> buscarPorTipo(@Param("tipo") String tipo);

    @Query("SELECT r FROM Recompensa r WHERE r.puntos_requeridos <= :puntosMaximos")
    List<Recompensa> buscarPorPuntosRequeridos(@Param("puntosMaximos") Integer puntosMaximos);

    // "disponibilidad" no existe en la entidad: la existencia real se llama "stock".
    @Query("SELECT r FROM Recompensa r WHERE r.activa = true AND r.stock > 0")
    List<Recompensa> buscarDisponibles();
}