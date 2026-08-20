package com.example.ReVueltaBack.servicios.usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.dtos.usuario.UsuarioRequestDTO;
import com.example.ReVueltaBack.dtos.usuario.UsuarioResponseDTO;
import com.example.ReVueltaBack.modelos.Usuario;
import com.example.ReVueltaBack.repositorios.IUsuarioRepositorio;
import com.example.ReVueltaBack.validaciones.usuario.IValidacionUsuario;

// HU USR-S01 — Servicio de Usuario.
// Aqui vive la LOGICA DE NEGOCIO: cifrar la contrasena, impedir correos
// repetidos, poner la fecha de registro y decidir que error HTTP devolver.
// El repositorio solo habla con la base de datos y el validador solo revisa
// los datos; ninguno de los dos toma decisiones de negocio.
@Service
public class ServicioUsuarioImpl implements IServicioUsuario {

    // Longitud minima que se le exige a la contrasena EN TEXTO PLANO.
    // (El validador revisa el HASH, que siempre mide 60 caracteres.)
    private static final int LONGITUD_MINIMA_CONTRASENA = 8;

    private final IUsuarioRepositorio repositorioUsuario;
    private final IValidacionUsuario validacionUsuario;

    // BCrypt: algoritmo estandar para guardar contrasenas. Produce siempre un
    // hash de 60 caracteres y nunca se puede "descifrar", solo comparar.
    private final PasswordEncoder codificador = new BCryptPasswordEncoder();

    // Inyeccion POR CONSTRUCTOR: Spring entrega el repositorio y el validador.
    public ServicioUsuarioImpl(IUsuarioRepositorio repositorioUsuario, IValidacionUsuario validacionUsuario) {
        this.repositorioUsuario = repositorioUsuario;
        this.validacionUsuario = validacionUsuario;
    }

    @Override
    public UsuarioResponseDTO registrar(UsuarioRequestDTO datos) {

        // 1. La contrasena llega en texto plano: se revisa y se cifra ANTES de
        //    construir la entidad. Asi la entidad nunca ve el texto plano.
        String contrasenaHash = codificador.encode(exigirContrasenaValida(datos.contrasena()));

        // 2. Convertir el DTO en la entidad, ya con el hash calculado.
        Usuario nuevoUsuario = datos.toEntity(contrasenaHash);

        // 3. Aplicar las validaciones de la HU USR-V01.
        validacionUsuario.validarUsuario(nuevoUsuario);

        // 4. Regla de negocio: el correo es unico. Se avisa con 409 CONFLICT
        //    en vez de dejar que reviente la restriccion de la base de datos.
        if (repositorioUsuario.existsByCorreo(nuevoUsuario.getCorreo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe un usuario registrado con el correo " + nuevoUsuario.getCorreo());
        }

        // 5. La fecha de registro la pone el servidor, nunca el cliente.
        nuevoUsuario.setFecha_registro(LocalDateTime.now());

        return UsuarioResponseDTO.fromEntity(repositorioUsuario.save(nuevoUsuario));
    }

    @Override
    public List<UsuarioResponseDTO> listar() {
        return repositorioUsuario.findAll().stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public UsuarioResponseDTO buscarPorId(UUID id) {
        return UsuarioResponseDTO.fromEntity(obtenerOFallar(id));
    }

    @Override
    public UsuarioResponseDTO buscarPorCorreo(String correo) {
        Usuario usuario = repositorioUsuario.buscarPorCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No hay ningun usuario con el correo " + correo));

        return UsuarioResponseDTO.fromEntity(usuario);
    }

    @Override
    public UsuarioResponseDTO actualizar(UUID id, UsuarioRequestDTO datos) {

        // 1. Solo se actualiza lo que existe.
        Usuario usuarioExistente = obtenerOFallar(id);

        // 2. Se construye una entidad temporal con los datos nuevos para poder
        //    validarlos con las MISMAS reglas del registro. Se reutiliza el hash
        //    actual porque la contrasena no se cambia desde aqui.
        Usuario datosNuevos = datos.toEntity(usuarioExistente.getContrasena_hash());
        validacionUsuario.validarUsuario(datosNuevos);

        // 3. Si cambio el correo, hay que revisar de nuevo que no este ocupado.
        String correoNuevo = datosNuevos.getCorreo();
        if (!correoNuevo.equalsIgnoreCase(usuarioExistente.getCorreo())
                && repositorioUsuario.existsByCorreo(correoNuevo)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe otro usuario registrado con el correo " + correoNuevo);
        }

        // 4. Se copian los campos editables sobre la entidad gestionada por JPA.
        //    NO se reemplaza el objeto entero: eso borraria id, fecha de registro
        //    y las relaciones que ya tiene cargadas.
        usuarioExistente.setNombre(datosNuevos.getNombre());
        usuarioExistente.setCorreo(correoNuevo);
        usuarioExistente.setRol(datosNuevos.getRol());
        usuarioExistente.setActivo(datosNuevos.getActivo());
        usuarioExistente.setColor_avatar(datosNuevos.getColor_avatar());

        return UsuarioResponseDTO.fromEntity(repositorioUsuario.save(usuarioExistente));
    }

    @Override
    public UsuarioResponseDTO cambiarContrasena(UUID id, String contrasenaNueva) {

        Usuario usuario = obtenerOFallar(id);

        usuario.setContrasena_hash(codificador.encode(exigirContrasenaValida(contrasenaNueva)));

        // Se revalida para dejar constancia de que el hash quedo bien formado.
        validacionUsuario.validarUsuario(usuario);

        return UsuarioResponseDTO.fromEntity(repositorioUsuario.save(usuario));
    }

    @Override
    public void eliminar(UUID id) {
        if (!repositorioUsuario.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con id " + id);
        }
        repositorioUsuario.deleteById(id);
    }

    // ===== Metodos privados de apoyo =====

    // Busca el usuario o corta la ejecucion con un 404. Evita repetir el mismo
    // orElseThrow en cada operacion.
    private Usuario obtenerOFallar(UUID id) {
        return repositorioUsuario.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuario no encontrado con id " + id));
    }

    // Revisa la contrasena en texto plano antes de cifrarla y la devuelve.
    private String exigirContrasenaValida(String contrasenaPlana) {
        if (contrasenaPlana == null || contrasenaPlana.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La contrasena es obligatoria");
        }
        if (contrasenaPlana.length() < LONGITUD_MINIMA_CONTRASENA) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La contrasena debe tener al menos " + LONGITUD_MINIMA_CONTRASENA + " caracteres");
        }
        return contrasenaPlana;
    }
}
