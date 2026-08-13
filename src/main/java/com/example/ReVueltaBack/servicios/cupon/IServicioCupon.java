package com.example.ReVueltaBack.servicios.cupon;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.cupon.CuponRequestDTO;
import com.example.ReVueltaBack.dtos.cupon.CuponResponseDTO;

public interface IServicioCupon {

    CuponResponseDTO registrar(CuponRequestDTO datos);
    List<CuponResponseDTO> listar();
    List<CuponResponseDTO> listarValidos();
    CuponResponseDTO buscarPorId(UUID id);
    CuponResponseDTO actualizar(UUID id, CuponRequestDTO datos);
    void eliminar(UUID id);
}
