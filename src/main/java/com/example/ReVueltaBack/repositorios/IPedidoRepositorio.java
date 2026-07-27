package com.example.ReVueltaBack.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.Pedido;
import java.util.List;
import java.time.LocalDate;


@Repository
public interface IPedidoRepositorio extends JpaRepository<Pedido, UUID> {
    // Aquí puedes definir métodos personalizados para consultas relacionadas con pedidos si es necesario

    //consultas personalizadas con JPA (solo lectura)
    List<Pedido> findByFecha(LocalDate fecha);
    
    List<Pedido> findByEstado(String estado);

    List<Pedido> findByUsuarioId(UUID usuarioId);

    List<Pedido> findByOrderByFechaDesc();

    List<Pedido> findByTotalGreaterThanEqual(Double totalMinimo);


    //consultas personalizadas con JPQL (todas habilitadas)
    @Query("SELECT p FROM Pedido p WHERE p.fecha = :fecha")
    List<Pedido> buscarPorFecha(@Param("fecha") LocalDate fecha);

    @Query("SELECT p FROM Pedido p WHERE p.usuario.id = :usuarioId")
    List<Pedido> buscarPorUsuarioId(@Param("usuarioId") UUID usuarioId);


    // como hacer un insert con JPQL

    

    // 
}
