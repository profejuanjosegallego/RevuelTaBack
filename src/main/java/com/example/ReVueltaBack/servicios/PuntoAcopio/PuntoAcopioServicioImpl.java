package com.example.ReVueltaBack.servicios.PuntoAcopio;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.PuntoAcopio.PuntoAcopioRequestDTO;
import com.example.ReVueltaBack.dtos.PuntoAcopio.PuntoAcopioResponseDTO;
import com.example.ReVueltaBack.modelos.PuntoAcopio;
import com.example.ReVueltaBack.repositorios.IPuntoAcopioRepositorio;
import com.example.ReVueltaBack.validaciones.puntoacopio.IValidacionPuntoAcopio;

@Service
public class PuntoAcopioServicioImpl implements IPuntoAcopioServicio {

    private final IPuntoAcopioRepositorio repositorioPuntoAcopio;
    private final IValidacionPuntoAcopio validacionPuntoAcopio;

    public PuntoAcopioServicioImpl(IPuntoAcopioRepositorio repositorioPuntoAcopio,IValidacionPuntoAcopio validacionPuntoAcopio) {
        this.repositorioPuntoAcopio = repositorioPuntoAcopio;
        this.validacionPuntoAcopio = validacionPuntoAcopio;
    }

    @Override
    public PuntoAcopioResponseDTO crear(PuntoAcopioRequestDTO dto) {

        PuntoAcopio datosPuntoAcopio = dto.toEntity();
        validacionPuntoAcopio.validar(datosPuntoAcopio);

        return PuntoAcopioResponseDTO.fromEntity(repositorioPuntoAcopio.save(datosPuntoAcopio));
    }

    @Override
    public List<PuntoAcopioResponseDTO> listar() {

        return repositorioPuntoAcopio.findAll().stream()
                .map(PuntoAcopioResponseDTO::fromEntity)
                .toList();

    }

    @Override
    public PuntoAcopioResponseDTO buscarPorId(UUID id) {

        PuntoAcopio puntoAcopio = repositorioPuntoAcopio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Punto de acopio no encontrado"));
        return PuntoAcopioResponseDTO.fromEntity(puntoAcopio);
    }

    @Override
    public PuntoAcopioResponseDTO actualizar(UUID id, PuntoAcopioRequestDTO dto) {

        PuntoAcopio puntoAcopio = repositorioPuntoAcopio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Punto de acopio no encontrado"));

        PuntoAcopio datosNuevos = dto.toEntity();
        validacionPuntoAcopio.validar(datosNuevos);

        puntoAcopio.setNombre(datosNuevos.getNombre());
        puntoAcopio.setDireccion(datosNuevos.getDireccion());
        puntoAcopio.setCiudad(datosNuevos.getCiudad());
        puntoAcopio.setHorario(datosNuevos.getHorario());
        puntoAcopio.setCapacidad(datosNuevos.getCapacidad());
        puntoAcopio.setActivo(datosNuevos.getActivo());

        return PuntoAcopioResponseDTO.fromEntity(repositorioPuntoAcopio.save(puntoAcopio));
    }

    @Override
    public void eliminar(UUID id) {
        
        if (!repositorioPuntoAcopio.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Punto de acopio no encontrado");
        }
        repositorioPuntoAcopio.deleteById(id);
    }

}