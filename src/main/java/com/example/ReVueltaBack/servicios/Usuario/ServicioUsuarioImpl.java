package com.example.ReVueltaBack.servicios.Usuario;

import java.util.List;
import java.util.UUID;

import com.example.ReVueltaBack.dtos.Usuario.UsuarioRequestDTO;
import com.example.ReVueltaBack.dtos.Usuario.UsuarioResponseDTO;
import com.example.ReVueltaBack.modelos.Usuario;
import com.example.ReVueltaBack.repositorios.UsuarioRepository;
import com.example.ReVueltaBack.validaciones.usuario.IValidacionUsuario;

public class ServicioUsuarioImpl implements IServicioUsuario {


    //para inyectar creo tantas variables como elementos tenga que inyectar

    private final UsuarioRepository repositorioUsuario;
    private final IValidacionUsuario validacionUsuario;
    
    

    //constructor del servicio que inyecta las dependencias
    public ServicioUsuarioImpl(UsuarioRepository repositorioUsuario, IValidacionUsuario validacionUsuario) {
        this.repositorioUsuario = repositorioUsuario;
        this.validacionUsuario = validacionUsuario;   
    }

    @Override
    public UsuarioResponseDTO registrar(UsuarioRequestDTO datos) {


        //1. convertir los datos del usuariorequestdto en un modelo
        Usuario datosUsuario=datos.toEntity();

        //2. aplicar las validaciones
        validacionUsuario.validarUsuario(datosUsuario);

        //3. aplicar el metodo del repositorio (guardar / save)
        return UsuarioResponseDTO.fromEntity(repositorioUsuario.save(datosUsuario));

       
    }

    @Override
    public List<UsuarioResponseDTO> listar() {

        //1. aplicar el metodo del repositorio (buscar todo / findAll)
      return repositorioUsuario.findAll().stream()
       .map(UsuarioResponseDTO::fromEntity)
       .toList();

        
    }

    @Override
    public UsuarioResponseDTO buscarPorId(UUID id) {
        
    }

    @Override
    public UsuarioResponseDTO actualizar(UUID id, UsuarioRequestDTO datos) {
        
    }

    @Override
    public void eliminar(UUID id) {
       
    }

}
