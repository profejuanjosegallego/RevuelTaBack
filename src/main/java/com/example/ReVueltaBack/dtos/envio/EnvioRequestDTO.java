package com.example.ReVueltaBack.dtos.envio;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Envio;
import com.example.ReVueltaBack.modelos.Pedido;
import com.example.ReVueltaBack.modelos.PuntoAcopio;
import com.example.ReVueltaBack.modelos.Transportista;

public record EnvioRequestDTO(

    String codigoGuia,
    String estado,
    Double costo,
    LocalDate fechaDespacho,
    LocalDate fechaEntregaEstimada,
    Double peso,
    UUID idPedido,
    UUID idTransportista,
    UUID idPuntoAcopio

) {

    public Envio toEntity(){

        Envio envio = new Envio();
        Pedido pedido = new Pedido();
        Transportista transportista = new Transportista();
        PuntoAcopio puntoAcopio = new PuntoAcopio();

        pedido.setId(idPedido);
        transportista.setId(idTransportista);
        puntoAcopio.setId(idPuntoAcopio);

        envio.setCodigo_guia(codigoGuia);
        envio.setEstado(estado);
        envio.setCosto(costo);
        envio.setFecha_despacho(fechaDespacho);
        envio.setFecha_entrega_estimada(fechaEntregaEstimada);
        envio.setPeso_kg(peso);

        envio.setPedido(pedido);
        envio.setTransportista(transportista);
        envio.setPuntos_de_acopio(puntoAcopio);
        
        return envio;
        
    }

}
