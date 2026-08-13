package com.example.ReVueltaBack.validaciones.cupon;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.Cupon;

@Component
public class ValidacionCuponImpl implements IValidacionCupon {

    // Tipos de descuento soportados por un cupon
    private static final List<String> TIPOS_PERMITIDOS = List.of(
        "PORCENTAJE",
        "MONTO_FIJO",
        "ENVIO_GRATIS"
    );

    @Override
    public void validarCodigoObligatorio(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El código del cupón es obligatorio");
        }
    }

    @Override
    public void validarTipoPermitido(String tipo) {
        if (tipo == null || !TIPOS_PERMITIDOS.contains(tipo.toUpperCase().trim())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "El tipo de cupón no es válido. Tipos permitidos: " + TIPOS_PERMITIDOS);
        }
    }

    @Override
    public void validarValor(String tipo, String valor) {
        if (valor == null || valor.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El valor del cupón es obligatorio");
        }

        if ("PORCENTAJE".equalsIgnoreCase(tipo)) {
            try {
                double valorNumerico = Double.parseDouble(valor);
                if (valorNumerico <= 0 || valorNumerico > 100) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Para cupones de tipo PORCENTAJE, el valor debe estar entre 0 y 100");
                }
            } catch (NumberFormatException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El valor de un cupón de tipo PORCENTAJE debe ser numérico");
            }
        }
    }

    @Override
    public void validarUsos(Integer usosMaximos, Integer usosActuales) {
        if (usosMaximos == null || usosMaximos <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Los usos máximos del cupón deben ser mayores a 0");
        }
        if (usosActuales == null || usosActuales < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Los usos actuales del cupón no pueden ser negativos");
        }
        if (usosActuales > usosMaximos) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Los usos actuales no pueden superar los usos máximos");
        }
    }

    @Override
    public void validarFechaExpiracion(LocalDateTime fechaExpiracion) {
        if (fechaExpiracion == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "La fecha de expiración del cupón es obligatoria");
        }
    }

    @Override
    public void validarCampanaAsociada(Cupon cupon) {
        if (cupon.getCampana() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "El cupón debe estar asociado a una campaña existente");
        }
    }

    @Override
    public void validarCupon(Cupon cupon) {
        if (cupon == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cupón no puede ser nulo");
        }
        validarCodigoObligatorio(cupon.getCodigo());
        validarTipoPermitido(cupon.getTipo());
        validarValor(cupon.getTipo(), cupon.getValor());
        validarUsos(cupon.getUsos_maximos(), cupon.getUsos_actuales());
        validarFechaExpiracion(cupon.getFecha_expiracion());
        validarCampanaAsociada(cupon);
    }
}