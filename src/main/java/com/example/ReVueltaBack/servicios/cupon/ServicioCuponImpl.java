package com.example.ReVueltaBack.servicios.cupon;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.cupon.CuponRequestDTO;
import com.example.ReVueltaBack.dtos.cupon.CuponResponseDTO;
import com.example.ReVueltaBack.modelos.Campana;
import com.example.ReVueltaBack.modelos.Cupon;
import com.example.ReVueltaBack.repositorios.ICampanaRepositorio;
import com.example.ReVueltaBack.repositorios.ICuponRepositorio;
import com.example.ReVueltaBack.validaciones.cupon.IValidacionCupon;

@Service
public class ServicioCuponImpl implements IServicioCupon {

    // para inyectar creo tantas variables como elementos tenga que inyectar
    private final ICuponRepositorio repositorioCupon;
    private final ICampanaRepositorio repositorioCampana;
    private final IValidacionCupon validacionCupon;

    // constructor del servicio que inyecta las dependencias
    public ServicioCuponImpl(ICuponRepositorio repositorioCupon, ICampanaRepositorio repositorioCampana,
            IValidacionCupon validacionCupon) {
        this.repositorioCupon = repositorioCupon;
        this.repositorioCampana = repositorioCampana;
        this.validacionCupon = validacionCupon;
    }

    @Override
    public CuponResponseDTO registrar(CuponRequestDTO datos) {

        // 1. resolver la campaña asociada (relacion obligatoria)
        Campana campana = repositorioCampana.findById(datos.campanaId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "La campaña asociada al cupón no existe"));

        // 2. convertir los datos del DTO en un modelo
        Cupon cupon = datos.toEntity(campana);

        // 3. aplicar las validaciones
        validacionCupon.validarCupon(cupon);

        // 4. aplicar el metodo del repositorio (guardar / save)
        return CuponResponseDTO.fromEntity(repositorioCupon.save(cupon));
    }

    @Override
    public List<CuponResponseDTO> listar() {
        return repositorioCupon.findAll().stream()
            .map(CuponResponseDTO::fromEntity)
            .toList();
    }

    @Override
    public List<CuponResponseDTO> listarValidos() {
        return repositorioCupon.buscarCuponesValidos().stream()
            .map(CuponResponseDTO::fromEntity)
            .toList();
    }

    @Override
    public CuponResponseDTO buscarPorId(UUID id) {
        Cupon cupon = repositorioCupon.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cupón no encontrado"));
        return CuponResponseDTO.fromEntity(cupon);
    }

    @Override
    public CuponResponseDTO actualizar(UUID id, CuponRequestDTO datos) {
        Cupon cuponExistente = repositorioCupon.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cupón no encontrado"));

        Campana campana = repositorioCampana.findById(datos.campanaId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "La campaña asociada al cupón no existe"));

        Cupon cuponActualizado = datos.toEntity(campana);
        validacionCupon.validarCupon(cuponActualizado);

        cuponExistente.setCodigo(cuponActualizado.getCodigo());
        cuponExistente.setTipo(cuponActualizado.getTipo());
        cuponExistente.setValor(cuponActualizado.getValor());
        cuponExistente.setUsos_maximos(cuponActualizado.getUsos_maximos());
        cuponExistente.setUsos_actuales(cuponActualizado.getUsos_actuales());
        cuponExistente.setFecha_expiracion(cuponActualizado.getFecha_expiracion());
        cuponExistente.setCampana(campana);

        return CuponResponseDTO.fromEntity(repositorioCupon.save(cuponExistente));
    }

    @Override
    public void eliminar(UUID id) {
        if (!repositorioCupon.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cupón no encontrado");
        }
        repositorioCupon.deleteById(id);
    }
}
