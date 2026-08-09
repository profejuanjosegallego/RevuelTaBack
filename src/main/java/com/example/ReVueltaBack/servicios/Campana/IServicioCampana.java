package com.example.ReVueltaBack.servicios.Campana;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.Campana.CampanaRequestDTO;
import com.example.ReVueltaBack.dtos.Campana.CampanaResponseDTO;

public interface IServicioCampana {

    CampanaResponseDTO registrar(CampanaRequestDTO datos);
    List<CampanaResponseDTO> listar();
    List<CampanaResponseDTO> listarActivas();
    CampanaResponseDTO buscarPorId(UUID id);
    CampanaResponseDTO actualizar(UUID id, CampanaRequestDTO datos);
    void eliminar(UUID id);

}
