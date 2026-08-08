package com.example.ReVueltaBack.servicios.Usuario;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.ReVueltaBack.dtos.Usuario.UsuarioRequestDTO;
import com.example.ReVueltaBack.dtos.Usuario.UsuarioResponseDTO;
import com.example.ReVueltaBack.modelos.Usuario;
import com.example.ReVueltaBack.repositorios.UsuarioRepository;
import com.example.ReVueltaBack.validaciones.usuario.IValidacionUsuario;


@Service
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

        //1. buscar usando el repositorio el usuario cuyo id me suministraron
        //2. TIENE UN OPTIONAL (puede o no estar) que hago si no esta?
        //3. SI SI ESTA, utilizo ek DTO y retorno el resultado

        Usuario usuario=repositorioUsuario.findById(id)
        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado"));

        return UsuarioResponseDTO.fromEntity(usuario);

        
    }

    @Override
    public UsuarioResponseDTO actualizar(UUID id, UsuarioRequestDTO datos) {
        
        //1. Buscar si lo que voy a actualizar existe
        //2. Si si existe actualizo despues de validar l ainformacion
        //3. retorno el usuario dto repsonse que actualice

        Usuario usuario=repositorioUsuario.findById(id)
        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"usuario no encontrado"));

        usuario.setCorreo(datos.correo());
        usuario.setRol(datos.rol());

        validacionUsuario.validarUsuario(usuario);

        return UsuarioResponseDTO.fromEntity(repositorioUsuario.save(usuario));

    }

    @Override
    public void eliminar(UUID id) {

        //1. buscar si esta si no esta decir que hacer
        //2. si esta eliminelo
        if(!repositorioUsuario.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"usuario no encontrado");
        }
        repositorioUsuario.deleteById(id);
       
    }

    @Override
    public UsuarioResponseDTO buscarPorCorreo(String correo) {
       
        Usuario usuario=repositorioUsuario.buscarPorCorreo(correo)
        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"usuario no esta"));

        return  UsuarioResponseDTO.fromEntity(usuario);

    }

}
