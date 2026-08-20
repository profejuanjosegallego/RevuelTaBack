package com.example.ReVueltaBack.dtos.Transportista;

import com.example.ReVueltaBack.modelos.Transportista;

public record TransportistasRequestDTO(
    String nombre,
    String tipoVehiculo,
    String placa,
    String telefono,
    String zonaCobertura,
    Boolean disponible
) {

    public Transportista toEntity() {
        Transportista transportista = new Transportista();

        transportista.setNombre(nombre);
        transportista.setTipo_vehiculo(tipoVehiculo);
        transportista.setPlaca(placa);
        transportista.setTelefono(telefono);
        transportista.setZona_cobertura(zonaCobertura);
        transportista.setDisponible(disponible);

        return transportista;
    }
}