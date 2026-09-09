package com.example.ReVueltaBack.servicios.calificacion;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.calificacion.CalificacionRequestDTO;
import com.example.ReVueltaBack.dtos.calificacion.CalificacionResponseDTO;
import com.example.ReVueltaBack.modelos.Calificacion;
import com.example.ReVueltaBack.modelos.Resena;
import com.example.ReVueltaBack.repositorios.ICalificacionRepositorio;
import com.example.ReVueltaBack.repositorios.IResenaRepositorio;
import com.example.ReVueltaBack.validaciones.calificacion.IValidacionCalificacion;

@Service
public class ServicioCalificacionImpl implements IServicioCalificacion {

    private final ICalificacionRepositorio calificacionRepository;
    private final IResenaRepositorio resenaRepository;
    private final IValidacionCalificacion validador;

    public ServicioCalificacionImpl(
            ICalificacionRepositorio calificacionRepository,
            IResenaRepositorio resenaRepository,
            IValidacionCalificacion validador) {
        this.calificacionRepository = calificacionRepository;
        this.resenaRepository = resenaRepository;
        this.validador = validador;
    }

    @Override
    public CalificacionResponseDTO crear(CalificacionRequestDTO dto) {
        Resena resena = buscarResenaOFallar(dto.idResena());

        Calificacion calificacion = dto.toEntity(resena);

        // Se valida ANTES de guardar: si el puntaje esta fuera de 1..5 o la fecha
        // es futura, el validador corta con un 400.
        validador.validar(calificacion);

        calificacion = calificacionRepository.save(calificacion);

        return CalificacionResponseDTO.fromEntity(calificacion);
    }

    @Override
    public List<CalificacionResponseDTO> listar() {
        return calificacionRepository.findAll()
                .stream()
                .map(CalificacionResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<CalificacionResponseDTO> listarPorUsuario(UUID idUsuario) {
        return calificacionRepository.buscarCalificacionesPorUsuario(idUsuario)
                .stream()
                .map(CalificacionResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public CalificacionResponseDTO buscarPorId(UUID id) {
        return CalificacionResponseDTO.fromEntity(buscarOFallar(id));
    }

    @Override
    public CalificacionResponseDTO actualizar(UUID id, CalificacionRequestDTO dto) {
        Calificacion calificacion = buscarOFallar(id);

        Resena resena = buscarResenaOFallar(dto.idResena());

        calificacion.setPuntaje(dto.puntaje());
        calificacion.setDimension(dto.dimension());
        calificacion.setComentario_corto(dto.comentarioCorto());
        calificacion.setFecha(dto.fecha());
        calificacion.setVerificada(dto.verificada());
        calificacion.setPeso(dto.peso());
        calificacion.setResena(resena);

        validador.validar(calificacion);

        calificacion = calificacionRepository.save(calificacion);

        return CalificacionResponseDTO.fromEntity(calificacion);
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

    private Resena buscarResenaOFallar(UUID idResena) {
        if (idResena == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Debe indicar la reseña (reseñaID) a la que pertenece la calificacion");
        }
        return resenaRepository.findById(idResena)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe la reseña con id " + idResena));
    }
}