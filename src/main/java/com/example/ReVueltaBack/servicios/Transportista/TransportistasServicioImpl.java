package com.example.ReVueltaBack.servicios.Transportista;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.Transportista.TransportistasRequestDTO;
import com.example.ReVueltaBack.dtos.Transportista.TransportistasResponseDTO;
import com.example.ReVueltaBack.modelos.Transportista;
import com.example.ReVueltaBack.repositorios.TransportistasRepository;
import com.example.ReVueltaBack.validaciones.Transportista.ITransportistasValidador;

@Service
public class TransportistasServicioImpl implements ITransportistasServicio {

    // Inyección por constructor: campos private final, sin @Autowired sobre el campo.
    private final TransportistasRepository transportistasRepository;
    private final ITransportistasValidador transportistasValidador;

    public TransportistasServicioImpl(TransportistasRepository transportistasRepository,
            ITransportistasValidador transportistasValidador) {
        this.transportistasRepository = transportistasRepository;
        this.transportistasValidador = transportistasValidador;
    }

    @Override
    public TransportistasResponseDTO crear(TransportistasRequestDTO dto) {

        // 1. Convertir el DTO en entidad (POO, sin mapper).
        Transportista transportista = dto.toEntity();

        // 2. Validar ANTES de guardar; si no cumple, el validador lanza 400.
        transportistasValidador.validar(transportista);

        // 3. Persistir y devolver la respuesta como DTO.
        return TransportistasResponseDTO.fromEntity(transportistasRepository.save(transportista));
    }

    @Override
    public List<TransportistasResponseDTO> listar() {

        return transportistasRepository.findAll().stream()
                .map(TransportistasResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public TransportistasResponseDTO buscarPorId(UUID id) {

        Transportista transportista = transportistasRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transportista no encontrado"));

        return TransportistasResponseDTO.fromEntity(transportista);
    }

    @Override
    public TransportistasResponseDTO actualizar(UUID id, TransportistasRequestDTO dto) {

        // 1. Verificar que exista.
        Transportista transportista = transportistasRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transportista no encontrado"));

        // 2. Actualizar los datos de la entidad con lo recibido en el DTO.
        transportista.setNombre(dto.nombre());
        transportista.setTipo_vehiculo(dto.tipoVehiculo());
        transportista.setPlaca(dto.placa());
        transportista.setTelefono(dto.telefono());
        transportista.setZona_cobertura(dto.zonaCobertura());
        transportista.setDisponible(dto.disponible());

        // 3. Validar ANTES de guardar; si no cumple, el validador lanza 400.
        transportistasValidador.validar(transportista);

        // 4. Guardar y devolver la respuesta como DTO.
        return TransportistasResponseDTO.fromEntity(transportistasRepository.save(transportista));
    }

    @Override
    public void eliminar(UUID id) {

        if (!transportistasRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transportista no encontrado");
        }

        transportistasRepository.deleteById(id);
    }

}