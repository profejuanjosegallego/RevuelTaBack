package com.example.ReVueltaBack.servicios.Categoria;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.Categoria.CategoriaRequestDTO;
import com.example.ReVueltaBack.dtos.Categoria.CategoriaResponseDTO;

public interface IServicioCategoria {

    CategoriaResponseDTO crear(CategoriaRequestDTO dto);
    List<CategoriaResponseDTO>listar();
    CategoriaResponseDTO buscarPorId(UUID id);
    CategoriaResponseDTO actualizar(UUID id, CategoriaRequestDTO dto);
    void eliminar(UUID id);

}
