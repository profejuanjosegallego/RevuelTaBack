package com.example.ReVueltaBack.servicios.reporte;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.ReVueltaBack.dtos.reporte.ReportesRequestDTO;
import com.example.ReVueltaBack.dtos.reporte.ReportesResponseDTO;
import com.example.ReVueltaBack.repositorios.IReporteRepository;
import com.example.ReVueltaBack.validaciones.reporte.IReportesValidador;

import jakarta.persistence.EntityNotFoundException;
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
        
        return ReportesResponseDTO.fromEntity(reporteRepository.findById(id).orElseThrow((() -> new EntityNotFoundException("Reporte no encontrado"))));

    }

    @Override
    public ReportesResponseDTO actualizar(UUID id, ReportesRequestDTO dto) {
        
        reportesValidador.validar(dto.toEntity());
        return ReportesResponseDTO.fromEntity(reporteRepository.save(dto.toEntity()));

    }

    @Override
    public void eliminar(UUID id) {
        
        reporteRepository.deleteById(id);

    }

}
