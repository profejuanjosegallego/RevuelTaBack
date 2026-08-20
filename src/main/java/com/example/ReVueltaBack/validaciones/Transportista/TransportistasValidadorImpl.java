package com.example.ReVueltaBack.validaciones.Transportista;

import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.Transportista;

@Component
public class TransportistasValidadorImpl implements ITransportistasValidador {

    private static final int ZONA_COBERTURA_MIN_LONGITUD = 3;
    private static final int ZONA_COBERTURA_MAX_LONGITUD = 255;
    private static final Pattern PATRON_PLACA = Pattern.compile("^[A-Z]{3}[0-9]{3}$");

    @Override
    public void validarNombreObligatorio(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del transportista es obligatorio.");
        }
    }

    @Override
    public void validarZonaCoberturaLongitud(String zonaCobertura) {
        if (zonaCobertura == null || zonaCobertura.length() < ZONA_COBERTURA_MIN_LONGITUD || zonaCobertura.length() > ZONA_COBERTURA_MAX_LONGITUD) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La zona de cobertura debe tener entre 3 y 255 caracteres.");
        }
    }

    @Override
    public void validarPlacaFormato(String placa) {
        if (placa == null || !PATRON_PLACA.matcher(placa).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La placa debe tener el formato AAA123.");
        }
    }

    @Override
    public void validar(Transportista transportista) {
        validarNombreObligatorio(transportista.getNombre());
        validarZonaCoberturaLongitud(transportista.getZona_cobertura());
        validarPlacaFormato(transportista.getPlaca());
    }

}