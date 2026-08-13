package com.example.ReVueltaBack.servicios.seguimientoenvio;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.seguimientoenvio.SeguimientoEnvioRequestDTO;
import com.example.ReVueltaBack.dtos.seguimientoenvio.SeguimientoEnvioResponseDTO;
import com.example.ReVueltaBack.modelos.SeguimientoEnvio;
import com.example.ReVueltaBack.repositorios.ISeguimientoEnvioRepositorio;
import com.example.ReVueltaBack.validaciones.seguimientoEnvio.IValidacionSeguimientoEnvio;

@Service
public class SeguimientoEnvioServicioImpl implements ISeguimientoEnvioServicio {

    private final ISeguimientoEnvioRepositorio repositorioSeguimientoEnvio;
    private final IValidacionSeguimientoEnvio validacionSeguimientoEnvio;

    public SeguimientoEnvioServicioImpl(ISeguimientoEnvioRepositorio repositorioSeguimientoEnvio,
            IValidacionSeguimientoEnvio validacionSeguimientoEnvio) {
        this.repositorioSeguimientoEnvio = repositorioSeguimientoEnvio;
        this.validacionSeguimientoEnvio = validacionSeguimientoEnvio;
    }

    @Override
    public SeguimientoEnvioResponseDTO crear(SeguimientoEnvioRequestDTO dto) {

        SeguimientoEnvio datosSeguimientoEnvio = dto.toEntity();

        validacionSeguimientoEnvio.validar(datosSeguimientoEnvio);

        return SeguimientoEnvioResponseDTO.fromEntity(repositorioSeguimientoEnvio.save(datosSeguimientoEnvio));
    }

    @Override
    public List<SeguimientoEnvioResponseDTO> listar() {

        return repositorioSeguimientoEnvio.findAll().stream()
                .map(SeguimientoEnvioResponseDTO::fromEntity)
                .toList();

    }

    @Override
    public SeguimientoEnvioResponseDTO buscarPorId(UUID id) {

        SeguimientoEnvio seguimientoEnvio = repositorioSeguimientoEnvio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        return SeguimientoEnvioResponseDTO.fromEntity(seguimientoEnvio);
    }

    @Override
    public SeguimientoEnvioResponseDTO actualizar(UUID id, SeguimientoEnvioRequestDTO dto) {

        SeguimientoEnvio seguimientoEnvio = repositorioSeguimientoEnvio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        seguimientoEnvio.setDescripcion(dto.descripcion());
        seguimientoEnvio.setEstado(dto.estado());
        seguimientoEnvio.setFecha_hora(dto.fechaHora());
        seguimientoEnvio.setLatitud(dto.latitud());
        seguimientoEnvio.setLongitud(dto.longitud());
        seguimientoEnvio.setUbicacion(dto.ubicacion());

        validacionSeguimientoEnvio.validar(seguimientoEnvio);

        return SeguimientoEnvioResponseDTO.fromEntity(repositorioSeguimientoEnvio.save(seguimientoEnvio));
    }

    @Override
    public void eliminar(UUID id) {
        
        if (!repositorioSeguimientoEnvio.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Uusario no encontrado");
        }
        repositorioSeguimientoEnvio.deleteById(id);
    }

}
