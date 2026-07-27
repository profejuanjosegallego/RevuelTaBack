package com.example.ReVueltaBack.validaciones.trueque;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.Prenda;
import com.example.ReVueltaBack.modelos.Trueque;

@Component
public class ValidacionTruequeImpl implements IValidacionTrueque {

    // Longitud maxima del mensaje (coincide con la columna, largo 500)
    private static final int LONGITUD_MENSAJE = 500;

    // Estados permitidos para un trueque
    private static final List<String> ESTADOS_VALIDOS = List.of(
            "PENDIENTE", "ACEPTADO", "RECHAZADO", "CANCELADO"
    );

    @Override
    public void validarEstadoValido(String estado) {
        if (estado == null || estado.isBlank() || !ESTADOS_VALIDOS.contains(estado.toUpperCase())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El estado del trueque no es valido");
        }
    }

    @Override
    public void validarFechaPropuestaObligatoria(LocalDate fechaPropuesta) {
        if (fechaPropuesta == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de propuesta es obligatoria");
        }
    }

    @Override
    public void validarFechaRespuestaCoherente(LocalDate fechaPropuesta, LocalDate fechaRespuesta) {
        if (fechaRespuesta != null && fechaPropuesta != null && fechaRespuesta.isBefore(fechaPropuesta)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de respuesta no puede ser anterior a la fecha de propuesta");
        }
    }

    @Override
    public void validarMensajeLongitud(String mensaje) {
        if (mensaje != null && mensaje.length() > LONGITUD_MENSAJE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El mensaje supera la longitud permitida de " + LONGITUD_MENSAJE + " caracteres");
        }
    }

    @Override
    public void validarValorEstimadoPositivo(Double valorEstimado) {
        if (valorEstimado != null && valorEstimado < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El valor estimado no puede ser negativo");
        }
    }

    @Override
    public void validarPrendaOfrecidaObligatoria(Prenda prenda) {
        if (prenda == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La prenda ofrecida es obligatoria");
        }
    }

    @Override
    public void validarPrendaDeseadaObligatoria(Prenda prendaDeseada) {
        if (prendaDeseada == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La prenda deseada es obligatoria");
        }
    }

    @Override
    public void validarPrendasDiferentes(Prenda prenda, Prenda prendaDeseada) {
        if (prenda != null && prendaDeseada != null && prenda.getId().equals(prendaDeseada.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La prenda ofrecida y la prenda deseada no pueden ser la misma");
        }
    }

    @Override
    public void validarProponenteObligatorio(Object proponente) {
        if (proponente == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El proponente es obligatorio");
        }
    }

    @Override
    public void validarTrueque(Trueque trueque) {
        validarEstadoValido(trueque.getEstado());
        validarFechaPropuestaObligatoria(trueque.getFecha_propuesta());
        validarFechaRespuestaCoherente(trueque.getFecha_propuesta(), trueque.getFecha_respuesta());
        validarMensajeLongitud(trueque.getMensaje());
        validarValorEstimadoPositivo(trueque.getValor_estimado());
        validarPrendaOfrecidaObligatoria(trueque.getPrenda());
        validarPrendaDeseadaObligatoria(trueque.getPrendaDeseada());
        validarPrendasDiferentes(trueque.getPrenda(), trueque.getPrendaDeseada());
        validarProponenteObligatorio(trueque.getProponente());
    }

}