package com.example.ReVueltaBack.servicios.imagenprenda;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.imagenprenda.ImagenesPrendaRequestDTO;
import com.example.ReVueltaBack.dtos.imagenprenda.ImagenesPrendaResponseDTO;

public interface IImagenesPrendaServicio {

    ImagenesPrendaResponseDTO crear(ImagenesPrendaRequestDTO requestDTO);

    ImagenesPrendaResponseDTO obtenerPorId(UUID id);

    List<ImagenesPrendaResponseDTO> listarTodas();

    ImagenesPrendaResponseDTO actualizar(UUID id, ImagenesPrendaRequestDTO requestDTO);

    void eliminar(UUID id);

}