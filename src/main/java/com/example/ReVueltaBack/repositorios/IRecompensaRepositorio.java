package com.example.ReVueltaBack.repositorios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.ReVueltaBack.modelos.Recompensa;

@Repository
public interface IRecompensaRepositorio extends JpaRepository<Recompensa,UUID>{
    @Query("SELECT r FROM Recompensas r " +
        "WHERE LOWER(r.nombre) = LOWER(:nombre)")
    List<Recompensa>buscarPorNombre(@Param("nombre")String nombre);

    @Query ("SELECT r FROM Recompensas r " +
        "WHERE LOWER(r.descripcion) = LOWER(:descripcion)")
    List<Recompensa> buscarPorDescripcion(@Param("descripcion") String descripcion);

    @Query("SELECT r FROM Recompensas r " +
        "WHERE LOWER(r.tipo) = LOWER(:tipo)")
    List<Recompensa> buscarPorTipo(@Param("tipo") String tipo);

    @Query("SELECT r FROM Recompensas r WHERE r.puntosRequeridos = :puntosRequeridos")
    List<Recompensa> buscarPorPuntosRequeridos(@Param("puntosRequeridos") Integer puntosRequeridos);

    @Query("SELECT r FROM Recompensas r WHERE r.disponibilidad = :disponibilidad")
    List<Recompensa> buscarPorDisponibilidad(@Param("disponibilidad") Integer disponibilidad);

    @Query("SELECT r FROM Recompensas r WHERE r.activa = :activa")
    List<Recompensa> buscarPorActiva(@Param("activa") Boolean activa);
    

}
