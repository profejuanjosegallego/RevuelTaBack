package com.example.ReVueltaBack.servicios.EstadoPrenda;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.estadoPrenda.EstadoPrendaRequestDTO;
import com.example.ReVueltaBack.dtos.estadoPrenda.EstadoPrendaResponseDTO;
import com.example.ReVueltaBack.modelos.EstadoPrenda;
import com.example.ReVueltaBack.repositorios.IEstadoPrendaRepositorio;
import com.example.ReVueltaBack.validaciones.estadoPrenda.IValidacionEstadoPrenda;

@Service
public class ServicioEstadoPrendaImpl implements IServicioEstadoPrenda {

    private final IEstadoPrendaRepositorio repositorioEstadoPrenda;
    private final IValidacionEstadoPrenda validacionEstadoPrenda;

    public ServicioEstadoPrendaImpl(IEstadoPrendaRepositorio repositorioEstadoPrenda,
                                    IValidacionEstadoPrenda validacionEstadoPrenda) {
        this.repositorioEstadoPrenda = repositorioEstadoPrenda;
        this.validacionEstadoPrenda = validacionEstadoPrenda;
    }

    @Override
    public EstadoPrendaResponseDTO registrar(EstadoPrendaRequestDTO datos) {
        EstadoPrenda estadoPrenda = datos.toEntity();

        validacionEstadoPrenda.validar(estadoPrenda);

        return EstadoPrendaResponseDTO.fromEntity(repositorioEstadoPrenda.save(estadoPrenda));
    }

    @Override
    public List<EstadoPrendaResponseDTO> listar() {
        return repositorioEstadoPrenda.findAll().stream()
            .map(EstadoPrendaResponseDTO::fromEntity)
            .toList();
    }

    @Override
    public List<EstadoPrendaResponseDTO> listarQueRequierenRevision() {
        return repositorioEstadoPrenda.findByRequiereRevisionTrue().stream()
            .map(EstadoPrendaResponseDTO::fromEntity)
            .toList();
    }

    @Override
    public List<EstadoPrendaResponseDTO> buscarPorNombre(String texto) {
        return repositorioEstadoPrenda.buscarPorNombre(texto).stream()
            .map(EstadoPrendaResponseDTO::fromEntity)
            .toList();
    }

    @Override
    public EstadoPrendaResponseDTO buscarPorId(UUID id) {
        EstadoPrenda estadoPrenda = repositorioEstadoPrenda.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado de prenda no encontrado"));
        return EstadoPrendaResponseDTO.fromEntity(estadoPrenda);
    }

    @Override
    public EstadoPrendaResponseDTO actualizar(UUID id, EstadoPrendaRequestDTO datos) {
        EstadoPrenda estadoExistente = repositorioEstadoPrenda.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado de prenda no encontrado"));

        EstadoPrenda estadoActualizado = datos.toEntity();
        validacionEstadoPrenda.validar(estadoActualizado);

        estadoExistente.setNombre(estadoActualizado.getNombre());
        estadoExistente.setDescripcion(estadoActualizado.getDescripcion());
        estadoExistente.setNivelDesgaste(estadoActualizado.getNivelDesgaste());
        estadoExistente.setColorEtiqueta(estadoActualizado.getColorEtiqueta());
        estadoExistente.setRequiereRevision(estadoActualizado.getRequiereRevision());
        estadoExistente.setActivo(estadoActualizado.getActivo());

        return EstadoPrendaResponseDTO.fromEntity(repositorioEstadoPrenda.save(estadoExistente));
    }

    @Override
    public void eliminar(UUID id) {
        if (!repositorioEstadoPrenda.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado de prenda no encontrado");
        }
        repositorioEstadoPrenda.deleteById(id);
    }

}
