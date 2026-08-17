package com.example.ReVueltaBack.servicios.trueque;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.trueque.TruequesRequestDTO;
import com.example.ReVueltaBack.dtos.trueque.TruequesResponseDTO;
import com.example.ReVueltaBack.modelos.Trueque;
import com.example.ReVueltaBack.repositorios.ITruequeRepositorio;
import com.example.ReVueltaBack.validaciones.trueque.IValidacionTrueque;


@Service
public class TruequesServicioImpl implements ITruequesServicio {

    private final ITruequeRepositorio truequeRepositorio;
    private final IValidacionTrueque validacionTrueque;

    public TruequesServicioImpl(ITruequeRepositorio truequeRepositorio,
                                 IValidacionTrueque validacionTrueque) {
        this.truequeRepositorio = truequeRepositorio;
        this.validacionTrueque = validacionTrueque;
    }

    @Override
    public TruequesResponseDTO crear(TruequesRequestDTO dto) {
        Trueque trueque = dto.toEntity();

        // Valida ANTES de guardar. Si algo no cumple, el validador lanza
        // ResponseStatusException(400) y aqui se propaga tal cual.
        validacionTrueque.validarTrueque(trueque);

        Trueque guardado = truequeRepositorio.save(trueque);
        return TruequesResponseDTO.fromEntity(guardado);
    }

    @Override
    public List<TruequesResponseDTO> listar() {
        return truequeRepositorio.findAll()
                .stream()
                .map(TruequesResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TruequesResponseDTO buscarPorId(UUID id) {
        Trueque trueque = truequeRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Trueque no encontrado con id: " + id));

        return TruequesResponseDTO.fromEntity(trueque);
    }

    @Override
    public TruequesResponseDTO actualizar(UUID id, TruequesRequestDTO dto) {
        Trueque existente = truequeRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Trueque no encontrado con id: " + id));

        Trueque actualizado = dto.toEntity();
        actualizado.setId(existente.getId());

        // Valida ANTES de guardar los cambios.
        validacionTrueque.validarTrueque(actualizado);

        Trueque guardado = truequeRepositorio.save(actualizado);
        return TruequesResponseDTO.fromEntity(guardado);
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
