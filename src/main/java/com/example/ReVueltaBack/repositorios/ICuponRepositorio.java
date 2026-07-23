package com.example.ReVueltaBack.repositorios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.Cupon;

@Repository
public interface ICuponRepositorio extends JpaRepository<Cupon, UUID> {

    @Query("SELECT c FROM Cupon c WHERE LOWER(c.codigo) = LOWER(:codigo)")
    Optional<Cupon> buscarPorCodigo(@Param("codigo") String codigo);

    @Query("SELECT c FROM Cupon c WHERE LOWER(c.tipo) = LOWER(:tipo)")
    List<Cupon> buscarPorTipo(@Param("tipo") String tipo);

    @Query("SELECT c FROM Cupon c WHERE LOWER(c.valor) = LOWER(:valor)")
    List<Cupon> buscarPorValor(@Param("valor") String valor);

    @Query("SELECT c FROM Cupon c WHERE c.usos_maximos = :usos_maximos")
    List<Cupon> buscarPorUsosMaximos(@Param("usos_maximos") Integer usosMaximos);

    @Query("SELECT c FROM Cupon c WHERE c.fecha_expiracion = :fecha_expiracion")
    List<Cupon> buscarPorFechaExpiracion(@Param("fecha_expiracion") LocalDateTime fechaExpiracion);

    @Query("SELECT c FROM Cupon c WHERE c.campana.id = :campanaId")
    List<Cupon> buscarPorCampanaId(@Param("campanaId") UUID campanaId);


    // Obtener cupones que aún son válidos (fecha vigente y que aún tengan usos disponibles)
    @Query("SELECT c FROM Cupon c WHERE c.fecha_expiracion > CURRENT_TIMESTAMP AND c.usos_actuales < c.usos_maximos")
    List<Cupon> buscarCuponesValidos();
}
