package com.example.ReVueltaBack.validaciones.DetallePedido;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.DetallePedido;

@Component
public class DetallePedidoValidadorImpl implements IDetallePedidoValidador {

    @Override
    public void validarEstadoItemObligatorio(String estadoItem) {

        if (estadoItem == null || estadoItem.trim().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El estado del item es obligatorio.");
        }

    }

    @Override
    public void validarSubtotalPositivo(Double subtotal) {

        if (subtotal == null || subtotal <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El subtotal debe ser mayor que cero.");
        }

    }

    @Override
    public void validarFechaNoFutura(LocalDate fecha) {

        if (fecha == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La fecha es obligatoria.");
        }

        if (fecha.isAfter(LocalDate.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La fecha no puede ser futura.");
        }

    }

    @Override
    public void validar(DetallePedido detallePedido) {

        validarEstadoItemObligatorio(detallePedido.getEstado_item());

        validarSubtotalPositivo(detallePedido.getSubtotal());

        validarFechaNoFutura(detallePedido.getFecha());

    }

}