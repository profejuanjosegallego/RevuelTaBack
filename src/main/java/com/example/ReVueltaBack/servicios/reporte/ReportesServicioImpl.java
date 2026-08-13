package com.example.ReVueltaBack.servicios.reporte;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.reporte.ReportesRequestDTO;
import com.example.ReVueltaBack.dtos.reporte.ReportesResponseDTO;
import com.example.ReVueltaBack.modelos.Reporte;
import com.example.ReVueltaBack.repositorios.IReporteRepository;
import com.example.ReVueltaBack.validaciones.reporte.IReportesValidador;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportesServicioImpl implements IReportesServicio {

    private final IReporteRepository reporteRepository;
    private final IReportesValidador reportesValidador;

    @Override
    public ReportesResponseDTO crear(ReportesRequestDTO dto) {

        reportesValidador.validar(dto.toEntity());
        return ReportesResponseDTO.fromEntity(reporteRepository.save(dto.toEntity()));

    }

    @Override
    public List<ReportesResponseDTO> listar() {
        
        return reporteRepository.findAll().stream().map(ReportesResponseDTO::fromEntity).toList();

    }

    @Override
    public ReportesResponseDTO buscarPorId(UUID id) {
        
        return ReportesResponseDTO.fromEntity(reporteRepository.findById(id).orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Reporte no encontrado"))));

    }

    @Override
    public ReportesResponseDTO actualizar(UUID id, ReportesRequestDTO dto) {
        
        reportesValidador.validar(dto.toEntity());
        Reporte reporteActual = reporteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporte no encontrado"));

        reporteActual.setMotivo(dto.motivo());
        reporteActual.setDescripcion(dto.descripcion());
        reporteActual.setEstado(dto.estado());
        reporteActual.setPrioridad(dto.prioridad());
        reporteActual.setFecha(dto.fecha());
        reporteActual.setResuelto(dto.resuelto());

        return ReportesResponseDTO.fromEntity(reporteRepository.save(reporteActual));

    }

    @Override
    public void eliminar(UUID id) {
        
        reporteRepository.deleteById(id);

    }

}
