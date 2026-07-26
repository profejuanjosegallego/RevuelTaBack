package com.example.ReVueltaBack.repositorios;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.DetallePedido;
import com.example.ReVueltaBack.modelos.Pedido;
import com.example.ReVueltaBack.modelos.Prenda;

public class DetallePedidoRepository {


;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, UUID> {

    List<DetallePedido> findByPedido(Pedido pedido);

    List<DetallePedido> findByPrenda(Prenda prenda);

    List<DetallePedido> findAllByOrderByFechaDesc();

    List<DetallePedido> findBySubtotalGreaterThanEqual(Double subtotalMinimo);

    @Query("SELECT d FROM DetallePedido d WHERE d.pedido.id = :pedidoId")
    List<DetallePedido> buscarPorPedidosId(@Param("pedidoId") UUID pedidoId);

}
 }