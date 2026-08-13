package com.example.ReVueltaBack.servicios.Reseña;


import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.Reseña.ReseñaRequestDTO;
import com.example.ReVueltaBack.dtos.Reseña.ReseñaResponseDTO;
import com.example.ReVueltaBack.modelos.Reseña;
import com.example.ReVueltaBack.repositorios.IReseñaRepositorio;
import com.example.ReVueltaBack.validaciones.reseña.IValidacionReseña;

@Service
public class ReseñaServicioImpl implements IReseñaServicio{

    private final IReseñaRepositorio repositorioReseña;
    private final IValidacionReseña validacionReseña;

    public ReseñaServicioImpl(IReseñaRepositorio repositorioReseña, IValidacionReseña validacionReseña) {
        this.repositorioReseña = repositorioReseña;
        this.validacionReseña = validacionReseña;
    }

    @Override
    public ReseñaResponseDTO crear(ReseñaRequestDTO dto) {

        Reseña reseña = dto.toEntity();

        validacionReseña.validar(reseña);

        return ReseñaResponseDTO.fromEntity(repositorioReseña.save(reseña));
    }

    @Override
    public List<ReseñaResponseDTO> listar() {
        return repositorioReseña.findAll().stream()
                .map(ReseñaResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public ReseñaResponseDTO buscarPorId(UUID id) {
        Reseña Reseña = repositorioReseña.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reseña no encontrada"));
        return ReseñaResponseDTO.fromEntity(Reseña);
    }

    @Override
    public ReseñaResponseDTO actualizar(UUID id, ReseñaRequestDTO datos) {
        Reseña reseñaExistente = repositorioReseña.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reseña no encontrada"));

        Reseña reseñaActualizada = datos.toEntity();
        validacionReseña.validar(reseñaActualizada);

        reseñaExistente.setTitulo(reseñaActualizada.getTitulo());
        reseñaExistente.setComentario(reseñaActualizada.getComentario());
        reseñaExistente.setFecha(reseñaActualizada.getFecha());
        reseñaExistente.setRecomendado(reseñaActualizada.getRecomendado());
        reseñaExistente.setEditada(reseñaActualizada.getEditada());
        reseñaExistente.setVisible(reseñaActualizada.getVisible());

        return ReseñaResponseDTO.fromEntity(repositorioReseña.save(reseñaExistente));
    }

    @Override
    public void eliminar(UUID id) {
        if (!repositorioReseña.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reseña no encontrada");
        }
        repositorioReseña.deleteById(id);
    }



}
