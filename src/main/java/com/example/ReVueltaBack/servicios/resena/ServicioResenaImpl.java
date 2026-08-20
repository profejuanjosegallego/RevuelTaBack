package com.example.ReVueltaBack.servicios.resena;


import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.resena.ResenaRequestDTO;
import com.example.ReVueltaBack.dtos.resena.ResenaResponseDTO;
import com.example.ReVueltaBack.modelos.Resena;
import com.example.ReVueltaBack.modelos.Usuario;
import com.example.ReVueltaBack.repositorios.IResenaRepositorio;
import com.example.ReVueltaBack.repositorios.IUsuarioRepositorio;
import com.example.ReVueltaBack.validaciones.resena.IValidacionResena;

@Service
public class ServicioResenaImpl implements IServicioResena{

    private final IResenaRepositorio repositorioResena;
    private final IUsuarioRepositorio repositorioUsuario;
    private final IValidacionResena validacionResena;

    public ServicioResenaImpl(IResenaRepositorio repositorioResena,
                              IUsuarioRepositorio repositorioUsuario,
                              IValidacionResena validacionResena) {
        this.repositorioResena = repositorioResena;
        this.repositorioUsuario = repositorioUsuario;
        this.validacionResena = validacionResena;
    }

    @Override
    public ResenaResponseDTO crear(ResenaRequestDTO dto) {

        // Las dos relaciones son obligatorias: se buscan de verdad en la BD y se
        // responde 404 si alguna no existe, en lugar de guardar referencias vacias.
        Usuario autor = buscarUsuarioOFallar(dto.idAutor(), "autor");
        Usuario usuarioResenado = buscarUsuarioOFallar(dto.idUsuarioResenado(), "usuario reseñado");

        if (autor.getId().equals(usuarioResenado.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Un usuario no puede reseñarse a si mismo");
        }

        Resena resena = dto.toEntity(autor, usuarioResenado);

        validacionResena.validar(resena);

        return ResenaResponseDTO.fromEntity(repositorioResena.save(resena));
    }

    @Override
    public List<ResenaResponseDTO> listar() {
        return repositorioResena.findAll().stream()
                .map(ResenaResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public ResenaResponseDTO buscarPorId(UUID id) {
        return ResenaResponseDTO.fromEntity(buscarResenaOFallar(id));
    }

    @Override
    public ResenaResponseDTO actualizar(UUID id, ResenaRequestDTO datos) {
        Resena resenaExistente = buscarResenaOFallar(id);

        // Se valida con los datos nuevos, reutilizando los usuarios ya guardados.
        Resena resenaActualizada = datos.toEntity(resenaExistente.getAutor(),
                                                  resenaExistente.getUsuarioResenado());
        validacionResena.validar(resenaActualizada);

        // Se copia campo por campo sobre la entidad que trajo JPA: asi no se
        // pierden el id ni las relaciones.
        resenaExistente.setTitulo(resenaActualizada.getTitulo());
        resenaExistente.setComentario(resenaActualizada.getComentario());
        resenaExistente.setFecha(resenaActualizada.getFecha());
        resenaExistente.setRecomendado(resenaActualizada.getRecomendado());
        resenaExistente.setEditada(resenaActualizada.getEditada());
        resenaExistente.setVisible(resenaActualizada.getVisible());

        return ResenaResponseDTO.fromEntity(repositorioResena.save(resenaExistente));
    }

    @Override
    public void eliminar(UUID id) {
        if (!repositorioResena.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reseña no encontrada");
        }
        repositorioResena.deleteById(id);
    }

    // ===== Metodos privados de apoyo =====

    private Resena buscarResenaOFallar(UUID id) {
        return repositorioResena.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reseña no encontrada"));
    }

    private Usuario buscarUsuarioOFallar(UUID id, String papel) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Debe indicar el id del " + papel + " de la reseña");
        }
        return repositorioUsuario.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe el usuario (" + papel + ") con id " + id));
    }
}
