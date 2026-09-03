package com.example.ReVueltaBack.servicios.reporte;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.reporte.ReporteRequestDTO;
import com.example.ReVueltaBack.dtos.reporte.ReporteResponseDTO;
import com.example.ReVueltaBack.modelos.Reporte;
import com.example.ReVueltaBack.repositorios.IReporteRepositorio;
import com.example.ReVueltaBack.validaciones.reporte.IValidacionReporte;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioReporteImpl implements IServicioReporte {

    private final IReporteRepositorio reporteRepository;
    private final IValidacionReporte reportesValidador;

    @Override
    public ReporteResponseDTO crear(ReporteRequestDTO dto) {

        reportesValidador.validar(dto.toEntity());
        return ReporteResponseDTO.fromEntity(reporteRepository.save(dto.toEntity()));

    }

    @Override
    public List<ReporteResponseDTO> listar() {
        
        return reporteRepository.findAll().stream().map(ReporteResponseDTO::fromEntity).toList();

    }

    @Override
    public List<ReporteResponseDTO> listarPorUsuarioReportado(UUID idUsuario) {
        return reporteRepository.buscarPorUsuarioReportado(idUsuario).stream()
                .map(ReporteResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public ReporteResponseDTO buscarPorId(UUID id) {
        
        return ReporteResponseDTO.fromEntity(reporteRepository.findById(id).orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Reporte no encontrado"))));

    }

    @Override
    public ReporteResponseDTO actualizar(UUID id, ReporteRequestDTO dto) {
        
        reportesValidador.validar(dto.toEntity());
        Reporte reporteActual = reporteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporte no encontrado"));

        reporteActual.setMotivo(dto.motivo());
        reporteActual.setDescripcion(dto.descripcion());
        reporteActual.setEstado(dto.estado());
        reporteActual.setPrioridad(dto.prioridad());
        reporteActual.setFecha(dto.fecha());
        reporteActual.setResuelto(dto.resuelto());

        return ReporteResponseDTO.fromEntity(reporteRepository.save(reporteActual));

    }

    @Override
    public void eliminar(UUID id) {
        
        reporteRepository.deleteById(id);

    }

}
