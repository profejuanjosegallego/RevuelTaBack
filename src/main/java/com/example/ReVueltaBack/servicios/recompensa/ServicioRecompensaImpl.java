package com.example.ReVueltaBack.servicios.recompensa;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.recompensa.RecompensaRequestDTO;
import com.example.ReVueltaBack.dtos.recompensa.RecompensaResponseDTO;
import com.example.ReVueltaBack.modelos.Recompensa;
import com.example.ReVueltaBack.repositorios.IRecompensaRepositorio;
import com.example.ReVueltaBack.validaciones.recompensa.IValidacionRecompensa;

@Service
public class ServicioRecompensaImpl implements IServicioRecompensa {

    private final IRecompensaRepositorio recompensaRepositorio;
    private final IValidacionRecompensa validacionRecompensa;

    public ServicioRecompensaImpl(IRecompensaRepositorio recompensaRepositorio, IValidacionRecompensa validacionRecompensa) {
        this.recompensaRepositorio = recompensaRepositorio;
        this.validacionRecompensa = validacionRecompensa;
    }

    @Override
    public RecompensaResponseDTO crear(RecompensaRequestDTO dto) {
        Recompensa recompensa = dto.toEntity();
        validacionRecompensa.validar(recompensa);
        Recompensa guardada = recompensaRepositorio.save(recompensa);
        return RecompensaResponseDTO.fromEntity(guardada);
    }

    @Override
    public List<RecompensaResponseDTO> listar() {
        return recompensaRepositorio.findAll()
                .stream()
                .map(RecompensaResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public RecompensaResponseDTO buscarPorId(UUID id) {
        Recompensa recompensa = recompensaRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, 
                        "La recompensa con ID " + id + " no fue encontrada."
                ));

        return RecompensaResponseDTO.fromEntity(recompensa);
    }

    @Override
    public RecompensaResponseDTO actualizar(UUID id, RecompensaRequestDTO dto) {
        Recompensa existente = recompensaRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, 
                        "No se puede actualizar. La recompensa con ID " + id + " no existe."
                ));

        Recompensa datosNuevos = dto.toEntity();
        validacionRecompensa.validar(datosNuevos);

        existente.setNombre(datosNuevos.getNombre());
        existente.setPuntos_requeridos(datosNuevos.getPuntos_requeridos());
        existente.setDescripcion(datosNuevos.getDescripcion());
        existente.setStock(datosNuevos.getStock());
        existente.setTipo(datosNuevos.getTipo());
        existente.setActiva(datosNuevos.getActiva());

        Recompensa guardada = recompensaRepositorio.save(existente);
        return RecompensaResponseDTO.fromEntity(guardada);
    }

    @Override
    public void eliminar(UUID id) {
        if (!recompensaRepositorio.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "No se puede eliminar. La recompensa con ID " + id + " no existe."
            );
        }

        recompensaRepositorio.deleteById(id);
    }
}