package com.example.ReVueltaBack.dtos.Transportista;

import java.util.UUID;

import com.example.ReVueltaBack.modelos.Transportista;

public record TransportistasResponseDTO(
    UUID id,
    String nombre,
    String tipoVehiculo,
    String placa,
    String telefono,
    String zonaCobertura,
    Boolean disponible
) {

    public static TransportistasResponseDTO fromEntity(Transportista transportista) {

        return new TransportistasResponseDTO(
            transportista.getId(),
            transportista.getNombre(),
            transportista.getTipo_vehiculo(),
            transportista.getPlaca(),
            transportista.getTelefono(),
            transportista.getZona_cobertura(),
            transportista.getDisponible()
        );
    }
}