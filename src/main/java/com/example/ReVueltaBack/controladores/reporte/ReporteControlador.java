package com.example.ReVueltaBack.controladores.reporte;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReVueltaBack.dtos.reporte.ReportesRequestDTO;
import com.example.ReVueltaBack.dtos.reporte.ReportesResponseDTO;
import com.example.ReVueltaBack.servicios.reporte.IReportesServicio;

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

    private final IReportesServicio reportesServicio;

    @PostMapping()
    public ResponseEntity<ReportesResponseDTO> Crear(@RequestBody ReportesRequestDTO entity) {        
        return ResponseEntity.ok(reportesServicio.crear(entity));
    }

    @GetMapping()
    public ResponseEntity<List<ReportesResponseDTO>> Listar() {
        return ResponseEntity.ok(reportesServicio.listar()) ;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ReportesResponseDTO> buscarId(@PathVariable UUID id) {
        return ResponseEntity.ok(reportesServicio.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportesResponseDTO> putMethodName(@PathVariable UUID id, @RequestBody ReportesRequestDTO entity) {
        return ResponseEntity.ok(reportesServicio.actualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        reportesServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    

}
