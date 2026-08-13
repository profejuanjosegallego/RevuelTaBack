package com.example.ReVueltaBack.repositorios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ReVueltaBack.modelos.Envio;
import com.example.ReVueltaBack.modelos.Pedido;
import com.example.ReVueltaBack.modelos.PuntoAcopio;
import com.example.ReVueltaBack.modelos.Transportista;

@Repository
public interface IEnvioRepositorio extends JpaRepository<Envio, UUID> {
    @Query("SELECT e FROM Envio e" + "WHERE e.pedido = :pedido")
    List<Envio> buscarPorPedido(@Param("pedido") Pedido pedido);

    List<Envio> findByTransportista(Transportista transportista);

    List<Envio> findByPuntoAcopio(PuntoAcopio puntoAcopio);

    //Consultas personalizadas con JPA (Solo lectura)
    //Optional<Envio> findByCodigoGuiaContainingIgnoreCase(String codigoGuia);

    //Consultas personalizadas con JPQL (Todas habilitadas)
    @Query("SELECT e FROM Envio e " + "WHERE LOWER(e.codigoGuia) = LOWER(:codigoGuia)")
    Optional<Envio> buscarPorCodigoGuia(@Param("codigoGuia") String codigoGuia);

    List<Envio> findByEstado(String estado);

    @Query("SELECT e FROM Envio e" + "WHERE e.pedido.id = pedidoId")
    List<Envio> buscarPorPedidoId (@Param("pedidoId") UUID pedidoId);
}
