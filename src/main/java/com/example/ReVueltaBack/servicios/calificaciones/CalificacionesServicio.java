package com.example.ReVueltaBack.servicios.calificaciones;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.calificaciones.CalificacionesRequestDTO;
import com.example.ReVueltaBack.dtos.calificaciones.CalificacionesResponseDTO;
import com.example.ReVueltaBack.modelos.Calificacion;
import com.example.ReVueltaBack.modelos.Reseña;
import com.example.ReVueltaBack.repositorios.CalificacionRepository;
import com.example.ReVueltaBack.repositorios.IReseñaRepositorio;
import com.example.ReVueltaBack.validaciones.calificaciones.ICalificacionesValidador;

@Service
public class CalificacionesServicio implements ICalificacionesServicio {

    private final CalificacionRepository calificacionRepository;
    private final IReseñaRepositorio reseñaRepository;
    private final ICalificacionesValidador validador;

    public CalificacionesServicio(
            CalificacionRepository calificacionRepository,
            IReseñaRepositorio reseñaRepository,
            ICalificacionesValidador validador) {
        this.calificacionRepository = calificacionRepository;
        this.reseñaRepository = reseñaRepository;
        this.validador = validador;
    }

    @Override
    public CalificacionesResponseDTO crear(CalificacionesRequestDTO dto) {
        Reseña reseña = buscarReseñaOFallar(dto.reseñaID());

        Calificacion calificacion = dto.toEntity(reseña);

        // Se valida ANTES de guardar: si el puntaje esta fuera de 1..5 o la fecha
        // es futura, el validador corta con un 400.
        validador.validar(calificacion);

        calificacion = calificacionRepository.save(calificacion);

        return CalificacionesResponseDTO.fromEntity(calificacion);
    }

    @Override
    public List<CalificacionesResponseDTO> listar() {
        return calificacionRepository.findAll()
                .stream()
                .map(CalificacionesResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public CalificacionesResponseDTO buscarPorId(UUID id) {
        return CalificacionesResponseDTO.fromEntity(buscarOFallar(id));
    }

    @Override
    public CalificacionesResponseDTO actualizar(UUID id, CalificacionesRequestDTO dto) {
        Calificacion calificacion = buscarOFallar(id);

        Reseña reseña = buscarReseñaOFallar(dto.reseñaID());

        calificacion.setPuntaje(dto.puntaje());
        calificacion.setDimension(dto.dimension());
        calificacion.setComentario_corto(dto.comentarioCorto());
        calificacion.setFecha(dto.fecha());
        calificacion.setVerificada(dto.verificada());
        calificacion.setPeso(dto.peso());
        calificacion.setReseña(reseña);

        validador.validar(calificacion);

        calificacion = calificacionRepository.save(calificacion);

        return CalificacionesResponseDTO.fromEntity(calificacion);
    }

    @Override
    public void eliminar(UUID id) {
        calificacionRepository.delete(buscarOFallar(id));
    }

    // ===== Metodos privados de apoyo =====
    // Se usa ResponseStatusException (no RuntimeException): asi la API responde
    // 404 Not Found en vez de 500 Internal Server Error.

    private Calificacion buscarOFallar(UUID id) {
        return calificacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Calificacion no encontrada con id " + id));
    }

    private Reseña buscarReseñaOFallar(UUID idReseña) {
        if (idReseña == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Debe indicar la reseña (reseñaID) a la que pertenece la calificacion");
        }
        return reseñaRepository.findById(idReseña)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe la reseña con id " + idReseña));
    }
}