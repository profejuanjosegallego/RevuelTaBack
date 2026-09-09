package com.example.ReVueltaBack.servicios.prenda;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.prenda.PrendaRequestDTO;
import com.example.ReVueltaBack.dtos.prenda.PrendaResponseDTO;


public interface IServicioPrenda {

    PrendaResponseDTO crear(PrendaRequestDTO dto);
    List<PrendaResponseDTO> listar();
    List<PrendaResponseDTO> listarPorUsuario(UUID idUsuario);
    List<PrendaResponseDTO> listarPorPublicadorYCompradorEntregado(UUID idPublicador, UUID idComprador);
    PrendaResponseDTO buscarPorId(UUID id);
    PrendaResponseDTO actualizar(UUID id, PrendaRequestDTO dto);
    void eliminar (UUID id);
}
