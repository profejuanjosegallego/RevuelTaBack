package com.example.ReVueltaBack.servicios.transaccion;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.transaccion.TransaccionRequestDTO;
import com.example.ReVueltaBack.dtos.transaccion.TransaccionResponseDTO;
import com.example.ReVueltaBack.modelos.Pedido;
import com.example.ReVueltaBack.modelos.Transaccion;
import com.example.ReVueltaBack.repositorios.ITransaccionRepositorio;
import com.example.ReVueltaBack.validaciones.transaccion.IValidacionTransaccion;

@Service
public class ServicioTransaccionImpl implements IServicioTransaccion {

    // para inyectar creo tantas variables como elementos tenga que inyectar
    private final ITransaccionRepositorio repositorioTransaccion;
    private final IValidacionTransaccion validacionTransaccion;

    // constructor del servicio que inyecta las dependencias
    public ServicioTransaccionImpl(ITransaccionRepositorio repositorioTransaccion,
            IValidacionTransaccion validacionTransaccion) {
        this.repositorioTransaccion = repositorioTransaccion;
        this.validacionTransaccion = validacionTransaccion;
    }

    @Override
    public TransaccionResponseDTO crear(TransaccionRequestDTO datos) {
        // 1. convertir los datos del TransaccionRequestDTO en un modelo
        Transaccion datosTransaccion = datos.toEntity();
        // 2. aplicar las validaciones
        validacionTransaccion.validar(datosTransaccion);
        // 3. aplicar el metodo del repositorio (guardar / save)
        return TransaccionResponseDTO.fromEntity(repositorioTransaccion.save(datosTransaccion));
    }

    @Override
    public List<TransaccionResponseDTO> listar() {
        // 1. aplicar el metodo del repositorio (buscar todo / findAll)
        return repositorioTransaccion.findAll().stream()
                .map(TransaccionResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public TransaccionResponseDTO buscarPorId(UUID id) {
        // 1. buscar usando el repositorio la transaccion cuyo id me suministraron
        // 2. TIENE UN OPTIONAL (puede o no estar) que hago si no esta?
        // 3. SI SI ESTA, utilizo el DTO y retorno el resultado
        Transaccion transaccion = repositorioTransaccion.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaccion no encontrada"));
        return TransaccionResponseDTO.fromEntity(transaccion);
    }

    @Override
    public TransaccionResponseDTO actualizar(UUID id, TransaccionRequestDTO datos) {
        // 1. Buscar si lo que voy a actualizar existe
        // 2. Si si existe actualizo despues de validar la informacion
        // 3. retorno el TransaccionResponseDTO que actualice
        Transaccion transaccion = repositorioTransaccion.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaccion no encontrada"));

        transaccion.setTipo(datos.tipo());
        transaccion.setMonto(datos.monto());
        transaccion.setEstado(datos.estado());
        transaccion.setReferencia_pago(datos.referenciaPago());
        transaccion.setFecha(datos.fecha());
        transaccion.setComprobante(datos.comprobante());

        Pedido pedido = new Pedido();
        pedido.setId(datos.idPedido());
        transaccion.setPedido(pedido);

        validacionTransaccion.validar(transaccion);
        return TransaccionResponseDTO.fromEntity(repositorioTransaccion.save(transaccion));
    }

    @Override
    public void eliminar(UUID id) {
        // 1. buscar si esta si no esta decir que hacer
        // 2. si esta eliminelo
        if (!repositorioTransaccion.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaccion no encontrada");
        }
        repositorioTransaccion.deleteById(id);
    }

}