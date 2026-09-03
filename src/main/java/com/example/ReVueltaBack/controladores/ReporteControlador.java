package com.example.ReVueltaBack.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReVueltaBack.dtos.reporte.ReporteRequestDTO;
import com.example.ReVueltaBack.dtos.reporte.ReporteResponseDTO;
import com.example.ReVueltaBack.servicios.reporte.IServicioReporte;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteControlador {

    private final IServicioReporte reportesServicio;

    @PostMapping()
    public ResponseEntity<ReporteResponseDTO> Crear(@RequestBody ReporteRequestDTO entity) {        
        return ResponseEntity.ok(reportesServicio.crear(entity));
    }

    @GetMapping()
    public ResponseEntity<List<ReporteResponseDTO>> Listar() {
        return ResponseEntity.ok(reportesServicio.listar()) ;
    }

    @GetMapping("/usuarioReportado/{id}")
    public ResponseEntity<List<ReporteResponseDTO>> listarPorUsuarioReportado(@PathVariable UUID id) {
        return ResponseEntity.ok(reportesServicio.listarPorUsuarioReportado(id));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ReporteResponseDTO> buscarId(@PathVariable UUID id) {
        return ResponseEntity.ok(reportesServicio.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteResponseDTO> putMethodName(@PathVariable UUID id, @RequestBody ReporteRequestDTO entity) {
        return ResponseEntity.ok(reportesServicio.actualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        reportesServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    

}
