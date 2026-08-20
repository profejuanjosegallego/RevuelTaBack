package com.example.ReVueltaBack.servicios.trueque;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.trueque.TruequeRequestDTO;
import com.example.ReVueltaBack.dtos.trueque.TruequeResponseDTO;
import com.example.ReVueltaBack.modelos.Trueque;
import com.example.ReVueltaBack.repositorios.ITruequeRepositorio;
import com.example.ReVueltaBack.validaciones.trueque.IValidacionTrueque;


@Service
public class ServicioTruequeImpl implements IServicioTrueque {

    private final ITruequeRepositorio truequeRepositorio;
    private final IValidacionTrueque validacionTrueque;

    public ServicioTruequeImpl(ITruequeRepositorio truequeRepositorio,
                                 IValidacionTrueque validacionTrueque) {
        this.truequeRepositorio = truequeRepositorio;
        this.validacionTrueque = validacionTrueque;
    }

    @Override
    public TruequeResponseDTO crear(TruequeRequestDTO dto) {
        Trueque trueque = dto.toEntity();

        // Valida ANTES de guardar. Si algo no cumple, el validador lanza
        // ResponseStatusException(400) y aqui se propaga tal cual.
        validacionTrueque.validarTrueque(trueque);

        Trueque guardado = truequeRepositorio.save(trueque);
        return TruequeResponseDTO.fromEntity(guardado);
    }

    @Override
    public List<TruequeResponseDTO> listar() {
        return truequeRepositorio.findAll()
                .stream()
                .map(TruequeResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TruequeResponseDTO buscarPorId(UUID id) {
        Trueque trueque = truequeRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Trueque no encontrado con id: " + id));

        return TruequeResponseDTO.fromEntity(trueque);
    }

    @Override
    public TruequeResponseDTO actualizar(UUID id, TruequeRequestDTO dto) {
        Trueque existente = truequeRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Trueque no encontrado con id: " + id));

        Trueque actualizado = dto.toEntity();
        actualizado.setId(existente.getId());

        // Valida ANTES de guardar los cambios.
        validacionTrueque.validarTrueque(actualizado);

        Trueque guardado = truequeRepositorio.save(actualizado);
        return TruequeResponseDTO.fromEntity(guardado);
    }

    @Override
    public void eliminar(UUID id) {
        if (!truequeRepositorio.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Trueque no encontrado con id: " + id);
        }
        truequeRepositorio.deleteById(id);
    }
}
