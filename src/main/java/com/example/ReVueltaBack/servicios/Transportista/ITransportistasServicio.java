package com.example.ReVueltaBack.servicios.Transportista;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.Transportista.TransportistasRequestDTO;
import com.example.ReVueltaBack.dtos.Transportista.TransportistasResponseDTO;

public interface ITransportistasServicio {

    TransportistasResponseDTO crear(TransportistasRequestDTO dto);
    List<TransportistasResponseDTO> listar();
    TransportistasResponseDTO buscarPorId(UUID id);
    TransportistasResponseDTO actualizar(UUID id, TransportistasRequestDTO dto);
    void eliminar(UUID id);

}