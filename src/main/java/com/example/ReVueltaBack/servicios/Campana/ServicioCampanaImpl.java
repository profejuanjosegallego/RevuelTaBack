package com.example.ReVueltaBack.servicios.Campana;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.Campana.CampanaRequestDTO;
import com.example.ReVueltaBack.dtos.Campana.CampanaResponseDTO;
import com.example.ReVueltaBack.modelos.Campana;
import com.example.ReVueltaBack.repositorios.ICampanaRepositorio;
import com.example.ReVueltaBack.validaciones.campana.IValidacionCampana;

@Service
public class ServicioCampanaImpl implements IServicioCampana{
    private final ICampanaRepositorio repositorioCampana;
    private final IValidacionCampana validacionCampana;

    public ServicioCampanaImpl(ICampanaRepositorio repositorioCampana, IValidacionCampana validacionCampana) {
        this.repositorioCampana = repositorioCampana;
        this.validacionCampana = validacionCampana;
    }

    @Override
    public CampanaResponseDTO registrar(CampanaRequestDTO datos) {

        Campana campana = datos.toEntity();

        validacionCampana.validarCampana(campana);

        return CampanaResponseDTO.fromEntity(repositorioCampana.save(campana));
    }

    @Override
    public List<CampanaResponseDTO> listar() {
        return repositorioCampana.findAll().stream()
            .map(CampanaResponseDTO::fromEntity)
            .toList();
    }

    @Override
    public List<CampanaResponseDTO> listarActivas() {
        return repositorioCampana.buscarActivas().stream()
            .map(CampanaResponseDTO::fromEntity)
            .toList();
    }

    @Override
    public CampanaResponseDTO buscarPorId(UUID id) {
        Campana campana = repositorioCampana.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Campaña no encontrada"));
        return CampanaResponseDTO.fromEntity(campana);
    }

    @Override
    public CampanaResponseDTO actualizar(UUID id, CampanaRequestDTO datos) {
        Campana campanaExistente = repositorioCampana.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Campaña no encontrada"));

        Campana campanaActualizada = datos.toEntity();
        validacionCampana.validarCampana(campanaActualizada);

        campanaExistente.setNombre_campana(campanaActualizada.getNombre_campana());
        campanaExistente.setDescripcion_campana(campanaActualizada.getDescripcion_campana());
        campanaExistente.setFecha_inicio(campanaActualizada.getFecha_inicio());
        campanaExistente.setFecha_final(campanaActualizada.getFecha_final());
        campanaExistente.setDescuento_pct(campanaActualizada.getDescuento_pct());
        campanaExistente.setActiva(campanaActualizada.getActiva());

        return CampanaResponseDTO.fromEntity(repositorioCampana.save(campanaExistente));
    }

    @Override
    public void eliminar(UUID id) {
        if (!repositorioCampana.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Campaña no encontrada");
        }
        repositorioCampana.deleteById(id);
    }


}
