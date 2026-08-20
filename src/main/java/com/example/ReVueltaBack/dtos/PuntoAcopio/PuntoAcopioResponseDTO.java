package com.example.ReVueltaBack.dtos.PuntoAcopio;

import java.util.UUID;

import com.example.ReVueltaBack.modelos.PuntoAcopio;

public record PuntoAcopioResponseDTO(

        UUID id,
        String nombre,
        String direccion,
        String ciudad,
        String horario,
        Integer capacidad,
        Boolean activo

) {

    public static PuntoAcopioResponseDTO fromEntity(PuntoAcopio puntoAcopio) {

        if (puntoAcopio == null) {
            return null;
        }

        return new PuntoAcopioResponseDTO(
                puntoAcopio.getId(),
                puntoAcopio.getNombre(),
                puntoAcopio.getDireccion(),
                puntoAcopio.getCiudad(),
                puntoAcopio.getHorario(),
                puntoAcopio.getCapacidad(),
                puntoAcopio.getActivo()
        );

    }
}