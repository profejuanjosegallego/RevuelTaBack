package com.example.ReVueltaBack.servicios.calificaciones;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.ReVueltaBack.dtos.calificaciones.CalificacionesRequestDTO;
import com.example.ReVueltaBack.dtos.calificaciones.CalificacionesResponseDTO;
import com.example.ReVueltaBack.modelos.Calificacion;
import com.example.ReVueltaBack.modelos.Reseña;
import com.example.ReVueltaBack.repositorios.CalificacionRepository;
import com.example.ReVueltaBack.repositorios.ReseñaRepository;

@Service
public class CalificacionesServicio implements ICalificacionesServicio {

    private final CalificacionRepository calificacionRepository;
    private final ReseñaRepository reseñaRepository;

    public CalificacionesServicio(
            CalificacionRepository calificacionRepository,
            ReseñaRepository reseñaRepository) {
        this.calificacionRepository = calificacionRepository;
        this.reseñaRepository = reseñaRepository;
    }

    @Override
    public CalificacionesResponseDTO crear(CalificacionesRequestDTO dto) {
        Reseña reseña = reseñaRepository.findById(dto.reseñaID())
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));

        Calificacion calificacion = dto.toEntity(reseña);
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
        Calificacion calificacion = calificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada"));

        return CalificacionesResponseDTO.fromEntity(calificacion);
    }

    @Override
    public CalificacionesResponseDTO actualizar(UUID id, CalificacionesRequestDTO dto) {
        Calificacion calificacion = calificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada"));

        Reseña reseña = reseñaRepository.findById(dto.reseñaID())
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));

        calificacion.setPuntaje(dto.puntaje());
        calificacion.setDimension(dto.dimension());
        calificacion.setComentario_corto(dto.comentarioCorto());
        calificacion.setFecha(dto.fecha());
        calificacion.setVerificada(dto.verificada());
        calificacion.setPeso(dto.peso());
        calificacion.setReseña(reseña);

        calificacion = calificacionRepository.save(calificacion);

        return CalificacionesResponseDTO.fromEntity(calificacion);
    }

    @Override
    public void eliminar(UUID id) {
        Calificacion calificacion = calificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada"));

        calificacionRepository.delete(calificacion);
    }
}