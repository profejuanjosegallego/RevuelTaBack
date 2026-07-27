package com.example.ReVueltaBack.dtos.Envio;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Envio;
import com.example.ReVueltaBack.modelos.Pedido;
import com.example.ReVueltaBack.modelos.PuntoAcopio;
import com.example.ReVueltaBack.modelos.Transportista;

public record EnvioResponseDTO(

    UUID id,
    String codigoGuia,
    String estado,
    Double costo,
    LocalDate fechaDespacho,
    LocalDate fechaEntregaEstimada,
    Double pesoKg,
    UUID idPedido,
    UUID idTransportista,
    UUID idPuntoAcopio

) {

    public EnvioResponseDTO fromEntity(Envio envio, Pedido pedido, Transportista transportista, PuntoAcopio puntoAcopio){

        return new EnvioResponseDTO(
        envio.getId(),
        envio.getCodigo_guia(),
        envio.getEstado(),
        envio.getCosto(),
        envio.getFecha_despacho(),
        envio.getFecha_entrega_estimada(),
        envio.getPesKg(),

        envio.getPedido(),
        envio.getTransportista(),
        envio.getPuntos_de_acopio()
        );
        
    }

}
