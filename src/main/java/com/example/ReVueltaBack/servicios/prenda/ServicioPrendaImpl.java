package com.example.ReVueltaBack.servicios.prenda;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.prenda.PrendaRequestDTO;
import com.example.ReVueltaBack.dtos.prenda.PrendaResponseDTO;
import com.example.ReVueltaBack.modelos.Prenda;
import com.example.ReVueltaBack.repositorios.IPrendaRepositorio;
import com.example.ReVueltaBack.validaciones.prenda.IPrendaValidador;

@Service
public class ServicioPrendaImpl implements IPrendaServicio {
    private final IPrendaRepositorio repositorioPrenda;
    private final IPrendaValidador validadorPrenda;

    public ServicioPrendaImpl(IPrendaRepositorio repositorioPrenda, IPrendaValidador validadorPrenda) {
        this.repositorioPrenda = repositorioPrenda;
        this.validadorPrenda = validadorPrenda;
    }

    @Override
    public PrendaResponseDTO crear(PrendaRequestDTO dto) {
        Prenda prenda = dto.toEntity();
        validadorPrenda.validar(prenda);
        return PrendaResponseDTO.fromEntity(repositorioPrenda.save(prenda));

    }

    @Override
    public List<PrendaResponseDTO> listar() {
        return repositorioPrenda.findAll().stream().map(PrendaResponseDTO::fromEntity).toList();
    }

    @Override
    public PrendaResponseDTO buscarPorId(UUID id) {
        Prenda prenda = repositorioPrenda.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prenda no encontrada"));
        return PrendaResponseDTO.fromEntity(prenda);
    }

    @Override
    public PrendaResponseDTO actualizar(UUID id, PrendaRequestDTO dto) {
        Prenda prenda = repositorioPrenda.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Prenda no encontrada"));

        prenda.setTitulo(dto.titulo());
        prenda.setDescripcion(dto.descripcion());
        prenda.setTalla(dto.talla());
        prenda.setPrecio(dto.precio());
        prenda.setFecha_publicacion(dto.fechaPublicacion());
        prenda.setDisponible(dto.disponible());

        validadorPrenda.validar(prenda);

        return PrendaResponseDTO.fromEntity(repositorioPrenda.save(prenda));
    }

    @Override
    public void eliminar(UUID id) {

        if (!repositorioPrenda.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Prenda no encontrada");
        }
        repositorioPrenda.deleteById(id);
    }

}
