package com.example.ReVueltaBack.servicios.Reseña;


import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.Reseña.ReseñaRequestDTO;
import com.example.ReVueltaBack.dtos.Reseña.ReseñaResponseDTO;
import com.example.ReVueltaBack.modelos.Reseña;
import com.example.ReVueltaBack.modelos.Usuario;
import com.example.ReVueltaBack.repositorios.IReseñaRepositorio;
import com.example.ReVueltaBack.repositorios.UsuarioRepository;
import com.example.ReVueltaBack.validaciones.reseña.IValidacionReseña;

@Service
public class ReseñaServicioImpl implements IReseñaServicio{

    private final IReseñaRepositorio repositorioReseña;
    private final UsuarioRepository repositorioUsuario;
    private final IValidacionReseña validacionReseña;

    public ReseñaServicioImpl(IReseñaRepositorio repositorioReseña,
                              UsuarioRepository repositorioUsuario,
                              IValidacionReseña validacionReseña) {
        this.repositorioReseña = repositorioReseña;
        this.repositorioUsuario = repositorioUsuario;
        this.validacionReseña = validacionReseña;
    }

    @Override
    public ReseñaResponseDTO crear(ReseñaRequestDTO dto) {

        // Las dos relaciones son obligatorias: se buscan de verdad en la BD y se
        // responde 404 si alguna no existe, en lugar de guardar referencias vacias.
        Usuario autor = buscarUsuarioOFallar(dto.idAutor(), "autor");
        Usuario usuarioReseñado = buscarUsuarioOFallar(dto.idUsuarioReseñado(), "usuario reseñado");

        if (autor.getId().equals(usuarioReseñado.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Un usuario no puede reseñarse a si mismo");
        }

        Reseña reseña = dto.toEntity(autor, usuarioReseñado);

        validacionReseña.validar(reseña);

        return ReseñaResponseDTO.fromEntity(repositorioReseña.save(reseña));
    }

    @Override
    public List<ReseñaResponseDTO> listar() {
        return repositorioReseña.findAll().stream()
                .map(ReseñaResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public ReseñaResponseDTO buscarPorId(UUID id) {
        return ReseñaResponseDTO.fromEntity(buscarReseñaOFallar(id));
    }

    @Override
    public ReseñaResponseDTO actualizar(UUID id, ReseñaRequestDTO datos) {
        Reseña reseñaExistente = buscarReseñaOFallar(id);

        // Se valida con los datos nuevos, reutilizando los usuarios ya guardados.
        Reseña reseñaActualizada = datos.toEntity(reseñaExistente.getAutor(),
                                                  reseñaExistente.getUsuarioReseñado());
        validacionReseña.validar(reseñaActualizada);

        // Se copia campo por campo sobre la entidad que trajo JPA: asi no se
        // pierden el id ni las relaciones.
        reseñaExistente.setTitulo(reseñaActualizada.getTitulo());
        reseñaExistente.setComentario(reseñaActualizada.getComentario());
        reseñaExistente.setFecha(reseñaActualizada.getFecha());
        reseñaExistente.setRecomendado(reseñaActualizada.getRecomendado());
        reseñaExistente.setEditada(reseñaActualizada.getEditada());
        reseñaExistente.setVisible(reseñaActualizada.getVisible());

        return ReseñaResponseDTO.fromEntity(repositorioReseña.save(reseñaExistente));
    }

    @Override
    public void eliminar(UUID id) {
        if (!repositorioReseña.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reseña no encontrada");
        }
        repositorioReseña.deleteById(id);
    }

    // ===== Metodos privados de apoyo =====

    private Reseña buscarReseñaOFallar(UUID id) {
        return repositorioReseña.findById(id)
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
