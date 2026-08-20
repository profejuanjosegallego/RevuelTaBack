package com.example.ReVueltaBack.servicios.seguimientoenvio;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.seguimientoenvio.SeguimientoEnvioRequestDTO;
import com.example.ReVueltaBack.dtos.seguimientoenvio.SeguimientoEnvioResponseDTO;
import com.example.ReVueltaBack.modelos.Envio;
import com.example.ReVueltaBack.modelos.SeguimientoEnvio;
import com.example.ReVueltaBack.repositorios.IEnvioRepositorio;
import com.example.ReVueltaBack.repositorios.ISeguimientoEnvioRepositorio;
import com.example.ReVueltaBack.validaciones.seguimientoEnvio.IValidacionSeguimientoEnvio;

@Service
public class SeguimientoEnvioServicioImpl implements ISeguimientoEnvioServicio {

    private final ISeguimientoEnvioRepositorio repositorioSeguimientoEnvio;
    private final IEnvioRepositorio repositorioEnvio;
    private final IValidacionSeguimientoEnvio validacionSeguimientoEnvio;

    public SeguimientoEnvioServicioImpl(ISeguimientoEnvioRepositorio repositorioSeguimientoEnvio,
            IEnvioRepositorio repositorioEnvio,
            IValidacionSeguimientoEnvio validacionSeguimientoEnvio) {
        this.repositorioSeguimientoEnvio = repositorioSeguimientoEnvio;
        this.repositorioEnvio = repositorioEnvio;
        this.validacionSeguimientoEnvio = validacionSeguimientoEnvio;
    }

    @Override
    public SeguimientoEnvioResponseDTO crear(SeguimientoEnvioRequestDTO dto) {

        // El envio es obligatorio: se busca de verdad en la BD y se responde 404
        // si no existe, en vez de dejar que reviente la llave foranea.
        Envio envio = buscarEnvioOFallar(dto.idEnvio());

        SeguimientoEnvio datosSeguimientoEnvio = dto.toEntity(envio);

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seguimiento de envio no encontrado"));
        return SeguimientoEnvioResponseDTO.fromEntity(seguimientoEnvio);
    }

    @Override
    public SeguimientoEnvioResponseDTO actualizar(UUID id, SeguimientoEnvioRequestDTO dto) {

        SeguimientoEnvio seguimientoEnvio = repositorioSeguimientoEnvio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seguimiento de envio no encontrado"));

        if (dto.idEnvio() != null) {
            seguimientoEnvio.setEnvio(buscarEnvioOFallar(dto.idEnvio()));
        }
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Seguimiento de envio no encontrado");
        }
        repositorioSeguimientoEnvio.deleteById(id);
    }

    private Envio buscarEnvioOFallar(java.util.UUID idEnvio) {
        if (idEnvio == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Debe indicar el idEnvio al que pertenece el seguimiento");
        }
        return repositorioEnvio.findById(idEnvio)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe el envio con id " + idEnvio));
    }

}
