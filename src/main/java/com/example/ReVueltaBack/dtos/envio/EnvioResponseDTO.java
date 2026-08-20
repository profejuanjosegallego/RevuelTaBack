package com.example.ReVueltaBack.dtos.envio;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Envio;


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

    public static EnvioResponseDTO fromEntity(Envio envio){

        return new EnvioResponseDTO(
        envio.getId(),
        envio.getCodigo_guia(),
        envio.getEstado(),
        envio.getCosto(),
        envio.getFecha_despacho(),
        envio.getFecha_entrega_estimada(),
        envio.getPesKg(),
        envio.getPedido().getId(),
        envio.getTransportista().getId(),
        envio.getPuntos_de_acopio().getId()

        );
        
    }

}
