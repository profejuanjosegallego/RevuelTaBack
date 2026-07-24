package com.example.ReVueltaBack.validaciones.pedido;

import java.util.regex.Pattern;

import com.example.ReVueltaBack.modelos.Pedido;

public class ValidacionPedidoImpl implements IValidacionPedido {
    
    // direccion
private static final Pattern DIRECCION_ENTREGA_OBLIGATORIO = Pattern.compile("^[\\w\\s\\-.,]+$");
    //

@Override
public void validarDireccionEntregaObligatorio(String direccionEntrega) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'validarDireccionEntregaObligatorio'");
}

@Override
public void validarNotasLongitud(String notas) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'validarNotasLongitud'");
}

@Override
public void validarTotalPositivo(double total) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'validarTotalPositivo'");
}

@Override
public void validarPedido(Pedido pedido) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'validarPedido'");
}


// Comento !!`


}
