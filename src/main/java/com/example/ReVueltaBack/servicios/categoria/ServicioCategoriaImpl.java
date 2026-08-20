package com.example.ReVueltaBack.servicios.categoria;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.categoria.CategoriaRequestDTO;
import com.example.ReVueltaBack.dtos.categoria.CategoriaResponseDTO;
import com.example.ReVueltaBack.modelos.Categoria;
import com.example.ReVueltaBack.repositorios.ICategoriaRepositorio;
import com.example.ReVueltaBack.validaciones.categoria.IValidacionCategoria;

@Service
public class ServicioCategoriaImpl implements IServicioCategoria {

    private final ICategoriaRepositorio repositorioCategoria;
    private final IValidacionCategoria validacionCategoria;

    public ServicioCategoriaImpl(ICategoriaRepositorio repositorioCategoria, IValidacionCategoria validacionCategoria) {
        this.repositorioCategoria = repositorioCategoria;
        this.validacionCategoria = validacionCategoria;
    }

    @Override
    public CategoriaResponseDTO crear(CategoriaRequestDTO dto) {
        Categoria datosCategoria = dto.toEntity();
        validacionCategoria.validarCategoria(datosCategoria);
        return CategoriaResponseDTO.fromEntity(repositorioCategoria.save(datosCategoria));
    }

    @Override
    public List<CategoriaResponseDTO> listar() {
        return repositorioCategoria.findAll().stream()
                .map(CategoriaResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public CategoriaResponseDTO buscarPorId(UUID id) {
        Categoria categoria = repositorioCategoria.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoría no encontrada con id: " + id));

        return CategoriaResponseDTO.fromEntity(categoria);
    }

    @Override
    public CategoriaResponseDTO actualizar(UUID id, CategoriaRequestDTO dto) {
        Categoria categoriaExistente = repositorioCategoria.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoría no encontrada con id: " + id));

        Categoria categoriaTemporal = dto.toEntity();
        validacionCategoria.validarCategoria(categoriaTemporal);

        categoriaExistente.setNombre(dto.nombre());
        categoriaExistente.setDescripcion(dto.descripcion());
        categoriaExistente.setSlug(dto.slug());
        categoriaExistente.setIcono(dto.icono());
        categoriaExistente.setActiva(dto.activa());
        categoriaExistente.setOrden(dto.orden());

        return CategoriaResponseDTO.fromEntity(repositorioCategoria.save(categoriaExistente));
    }

    @Override
    public void eliminar(UUID id) {
        Categoria categoria = repositorioCategoria.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoría no encontrada con id: " + id));

        repositorioCategoria.delete(categoria);
    }
}
