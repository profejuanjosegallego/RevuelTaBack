package com.example.ReVueltaBack.servicios.categoria;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.categoria.CategoriaRequestDTO;
import com.example.ReVueltaBack.dtos.categoria.CategoriaResponseDTO;

public interface IServicioCategoria {

    CategoriaResponseDTO crear(CategoriaRequestDTO dto);
    List<CategoriaResponseDTO>listar();
    CategoriaResponseDTO buscarPorId(UUID id);
    CategoriaResponseDTO actualizar(UUID id, CategoriaRequestDTO dto);
    void eliminar(UUID id);

}
