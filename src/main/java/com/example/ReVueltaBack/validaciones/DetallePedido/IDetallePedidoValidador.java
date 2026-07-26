package com.example.ReVueltaBack.validaciones.DetallePedido;

import java.time.LocalDate;

import com.example.ReVueltaBack.modelos.DetallePedido;

public interface IDetallePedidoValidador {

    void validarEstadoItemObligatorio(String estadoItem);

    void validarSubtotalPositivo(Double subtotal);

    void validarFechaNoFutura(LocalDate fecha);

    void validar(DetallePedido detallePedido);

}


