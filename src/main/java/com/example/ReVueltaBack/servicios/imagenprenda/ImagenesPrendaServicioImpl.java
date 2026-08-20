package com.example.ReVueltaBack.servicios.imagenprenda;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.imagenprenda.ImagenesPrendaRequestDTO;
import com.example.ReVueltaBack.dtos.imagenprenda.ImagenesPrendaResponseDTO;
import com.example.ReVueltaBack.modelos.ImagenPrenda;
import com.example.ReVueltaBack.repositorios.IMagenPrendaRepositorio;
import com.example.ReVueltaBack.validaciones.imagenprenda.IImagenesPrendaValidador;

@Service
public class ImagenesPrendaServicioImpl implements IImagenesPrendaServicio {

    private final IMagenPrendaRepositorio repositorio;
    private final IImagenesPrendaValidador validador;

    public ImagenesPrendaServicioImpl(IMagenPrendaRepositorio repositorio, IImagenesPrendaValidador validador) {
        this.repositorio = repositorio;
        this.validador = validador;
    }

    @Override
    public ImagenesPrendaResponseDTO crear(ImagenesPrendaRequestDTO requestDTO) {
        ImagenPrenda entidad = requestDTO.toEntity();
        validador.validar(entidad);
        ImagenPrenda guardada = repositorio.save(entidad);
        return ImagenesPrendaResponseDTO.fromEntity(guardada);
    }

    @Override
    public ImagenesPrendaResponseDTO obtenerPorId(UUID id) {
        ImagenPrenda entidad = repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No se encontró la imagen de prenda con id: " + id));
        return ImagenesPrendaResponseDTO.fromEntity(entidad);
    }

    @Override
    public List<ImagenesPrendaResponseDTO> listarTodas() {
        return repositorio.findAll()
                .stream()
                .map(ImagenesPrendaResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public ImagenesPrendaResponseDTO actualizar(UUID id, ImagenesPrendaRequestDTO requestDTO) {
        ImagenPrenda existente = repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No se encontró la imagen de prenda con id: " + id));

        ImagenPrenda datosNuevos = requestDTO.toEntity();
        existente.setUrl(datosNuevos.getUrl());
        existente.setEs_Principal(datosNuevos.getEs_Principal());
        existente.setOrden(datosNuevos.getOrden());
        existente.setFormato(datosNuevos.getFormato());
        existente.setTamaño_KB(datosNuevos.getTamaño_KB());
        existente.setPrenda(datosNuevos.getPrenda());

        validador.validar(existente);
        ImagenPrenda actualizada = repositorio.save(existente);
        return ImagenesPrendaResponseDTO.fromEntity(actualizada);
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