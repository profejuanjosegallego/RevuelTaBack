package com.example.ReVueltaBack.servicios.imagenprenda;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.imagenprenda.ImagenPrendaRequestDTO;
import com.example.ReVueltaBack.dtos.imagenprenda.ImagenPrendaResponseDTO;
import com.example.ReVueltaBack.modelos.ImagenPrenda;
import com.example.ReVueltaBack.repositorios.IImagenPrendaRepositorio;
import com.example.ReVueltaBack.validaciones.imagenprenda.IValidacionImagenPrenda;

@Service
public class ServicioImagenPrendaImpl implements IServicioImagenPrenda {

    private final IImagenPrendaRepositorio repositorio;
    private final IValidacionImagenPrenda validador;

    public ServicioImagenPrendaImpl(IImagenPrendaRepositorio repositorio, IValidacionImagenPrenda validador) {
        this.repositorio = repositorio;
        this.validador = validador;
    }

    @Override
    public ImagenPrendaResponseDTO crear(ImagenPrendaRequestDTO requestDTO) {
        ImagenPrenda entidad = requestDTO.toEntity();
        validador.validar(entidad);
        ImagenPrenda guardada = repositorio.save(entidad);
        return ImagenPrendaResponseDTO.fromEntity(guardada);
    }

    @Override
    public ImagenPrendaResponseDTO obtenerPorId(UUID id) {
        ImagenPrenda entidad = repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No se encontró la imagen de prenda con id: " + id));
        return ImagenPrendaResponseDTO.fromEntity(entidad);
    }

    @Override
    public List<ImagenPrendaResponseDTO> listarTodas() {
        return repositorio.findAll()
                .stream()
                .map(ImagenPrendaResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public ImagenPrendaResponseDTO actualizar(UUID id, ImagenPrendaRequestDTO requestDTO) {
        ImagenPrenda existente = repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No se encontró la imagen de prenda con id: " + id));

        ImagenPrenda datosNuevos = requestDTO.toEntity();
        existente.setUrl(datosNuevos.getUrl());
        existente.setEs_Principal(datosNuevos.getEs_Principal());
        existente.setOrden(datosNuevos.getOrden());
        existente.setFormato(datosNuevos.getFormato());
        existente.setTamano_KB(datosNuevos.getTamano_KB());
        existente.setPrenda(datosNuevos.getPrenda());

        validador.validar(existente);
        ImagenPrenda actualizada = repositorio.save(existente);
        return ImagenPrendaResponseDTO.fromEntity(actualizada);
    }

    @Override
    public void eliminar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se encontró la imagen de prenda con id: " + id);
        }
        repositorio.deleteById(id);
    }

}