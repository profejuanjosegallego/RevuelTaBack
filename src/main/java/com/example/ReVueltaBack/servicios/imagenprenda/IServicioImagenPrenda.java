package com.example.ReVueltaBack.servicios.imagenprenda;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.imagenprenda.ImagenPrendaRequestDTO;
import com.example.ReVueltaBack.dtos.imagenprenda.ImagenPrendaResponseDTO;

public interface IServicioImagenPrenda {

    ImagenPrendaResponseDTO crear(ImagenPrendaRequestDTO requestDTO);

    ImagenPrendaResponseDTO obtenerPorId(UUID id);

    List<ImagenPrendaResponseDTO> listarTodas();

    ImagenPrendaResponseDTO actualizar(UUID id, ImagenPrendaRequestDTO requestDTO);

    void eliminar(UUID id);

}