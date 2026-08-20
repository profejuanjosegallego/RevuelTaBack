package com.example.ReVueltaBack.servicios.campana;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.campana.CampanaRequestDTO;
import com.example.ReVueltaBack.dtos.campana.CampanaResponseDTO;

public interface IServicioCampana {

    CampanaResponseDTO registrar(CampanaRequestDTO datos);
    List<CampanaResponseDTO> listar();
    List<CampanaResponseDTO> listarActivas();
    CampanaResponseDTO buscarPorId(UUID id);
    CampanaResponseDTO actualizar(UUID id, CampanaRequestDTO datos);
    void eliminar(UUID id);

}
