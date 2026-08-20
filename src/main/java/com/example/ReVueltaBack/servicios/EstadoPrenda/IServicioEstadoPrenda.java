package com.example.ReVueltaBack.servicios.estadoprenda;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.estadoprenda.EstadoPrendaRequestDTO;
import com.example.ReVueltaBack.dtos.estadoprenda.EstadoPrendaResponseDTO;

public interface IServicioEstadoPrenda {

    EstadoPrendaResponseDTO registrar(EstadoPrendaRequestDTO datos);
    List<EstadoPrendaResponseDTO> listar();
    List<EstadoPrendaResponseDTO> listarQueRequierenRevision();
    List<EstadoPrendaResponseDTO> buscarPorNombre(String texto);
    EstadoPrendaResponseDTO buscarPorId(UUID id);
    EstadoPrendaResponseDTO actualizar(UUID id, EstadoPrendaRequestDTO datos);
    void eliminar(UUID id);

}
