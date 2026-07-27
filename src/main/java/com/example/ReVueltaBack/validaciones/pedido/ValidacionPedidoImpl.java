package com.example.ReVueltaBack.validaciones.pedido;

import org.springframework.http.HttpStatus;

//import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.Pedido;

@Component
public class ValidacionPedidoImpl implements IValidacionPedido {
    
    // direccion
// private static final Pattern DIRECCION_ENTREGA_OBLIGATORIO = Pattern.compile("^[\\w\\s\\-.,]+$");
    //

@Override
public void validarDireccionEntregaObligatorio(String direccionEntrega) {

    if (direccionEntrega == null || direccionEntrega.isBlank()) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "La dirección de entrega es obligatoria"
        );
    }
}

@Override
public void validarNotasLongitud(String notas) {

    if (notas != null && (notas.length() < 3 || notas.length() > 255)) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Las notas deben tener entre 3 y 255 caracteres"
        );
    }
}

@Override
public void validarTotalPositivo(Double total) {
    if (total == null || total <= 0) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "El total del pedido debe ser un valor positivo"
        );
    }
}

@Override
public void validarPedido(Pedido pedido) {
    validarDireccionEntregaObligatorio(pedido.getDireccion_entrega());

    validarNotasLongitud(pedido.getNotas());
    
    validarTotalPositivo(pedido.getTotal());
}





}
