package com.example.ReVueltaBack.servicios.envio;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.envio.EnvioRequestDTO;
import com.example.ReVueltaBack.dtos.envio.EnvioResponseDTO;
import com.example.ReVueltaBack.modelos.Envio;
import com.example.ReVueltaBack.repositorios.IEnvioRepositorio;
import com.example.ReVueltaBack.validaciones.envio.IValidacionEnvio;

@Service
public class ServicioEnvioImpl  implements IServicioEnvio{

    private final IEnvioRepositorio repositorioEnvio;
    private final IValidacionEnvio validacionEnvio;

    public ServicioEnvioImpl(IEnvioRepositorio repositorioEnvio, IValidacionEnvio validacionEnvio) {
        this.repositorioEnvio = repositorioEnvio;
        this.validacionEnvio = validacionEnvio;
    }

    @Override
    public EnvioResponseDTO crear(EnvioRequestDTO dto) {
        
        Envio datosEnvio = dto.toEntity();
        validacionEnvio.validarEnvio(datosEnvio);

        return EnvioResponseDTO.fromEntity(repositorioEnvio.save(datosEnvio));
    }

    @Override
    public List<EnvioResponseDTO> listar() {

        return repositorioEnvio.findAll().stream().map(EnvioResponseDTO::fromEntity).toList();
    }

    @Override
    public EnvioResponseDTO buscarPorId(UUID id) {

        Envio envio = repositorioEnvio.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Envío no encontrado"));

        return EnvioResponseDTO.fromEntity(envio);
    }

    @Override
    public EnvioResponseDTO actualizar(UUID id, EnvioRequestDTO dto) {

        Envio envio = repositorioEnvio.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Envío no encontrado"));

        envio.setCosto(dto.costo());
        envio.setEstado(dto.estado());
        envio.setFecha_despacho(dto.fechaDespacho());
        envio.setFecha_entrega_estimada(dto.fechaEntregaEstimada());
        envio.setPeso_kg(dto.peso());

        validacionEnvio.validarEnvio(envio);

        return EnvioResponseDTO.fromEntity(repositorioEnvio.save(envio));
        
    }

    @Override
    public void eliminar(UUID id) {

        if (!repositorioEnvio.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Envio no encontrado");
        }
        repositorioEnvio.deleteById(id);
    }



}
