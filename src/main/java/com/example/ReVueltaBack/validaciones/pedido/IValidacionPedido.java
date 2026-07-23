package com.example.ReVueltaBack.validaciones.pedido;

import com.example.ReVueltaBack.modelos.Pedido;

public interface IValidacionPedido {

    void validarDireccionEntregaObligatorio(String direccionEntrega);

    void validarNotasLongitud(String notas);

    void validarTotalPositivo(double total);

    void validarPedido(Pedido pedido);


}
