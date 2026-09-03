package com.example.ReVueltaBack.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.ReVueltaBack.modelos.Calificacion;
import com.example.ReVueltaBack.modelos.Campana;
import com.example.ReVueltaBack.modelos.Categoria;
import com.example.ReVueltaBack.modelos.Cupon;
import com.example.ReVueltaBack.modelos.DetallePedido;
import com.example.ReVueltaBack.modelos.Envio;
import com.example.ReVueltaBack.modelos.EstadoPrenda;
import com.example.ReVueltaBack.modelos.ImagenPrenda;
import com.example.ReVueltaBack.modelos.Pedido;
import com.example.ReVueltaBack.modelos.Prenda;
import com.example.ReVueltaBack.modelos.PuntoAcopio;
import com.example.ReVueltaBack.modelos.Recompensa;
import com.example.ReVueltaBack.modelos.Reporte;
import com.example.ReVueltaBack.modelos.Resena;
import com.example.ReVueltaBack.modelos.SeguimientoEnvio;
import com.example.ReVueltaBack.modelos.Transaccion;
import com.example.ReVueltaBack.modelos.Transportista;
import com.example.ReVueltaBack.modelos.Trueque;
import com.example.ReVueltaBack.modelos.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Carga de datos de prueba para la clase.
 *
 * Solo se ejecuta si la base de datos esta VACIA, asi que se puede reiniciar la
 * aplicacion las veces que haga falta sin duplicar informacion. Para empezar de
 * cero: apagar la app y borrar la carpeta ./data.
 *
 * Sirve para tener ya creados los usuarios, categorias y estados que las demas
 * tablas necesitan como llave foranea; sin esto no se puede probar ni un POST.
 *
 * Se usa EntityManager (y no los repositorios) para poder insertar tambien las
 * tablas cuyo repositorio todavia no ha entregado su equipo.
 */
@Component
public class CargaDatosIniciales implements CommandLineRunner {

    @PersistenceContext
    private EntityManager em;

    private final BCryptPasswordEncoder codificador = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public void run(String... args) {

        Long usuariosExistentes = em.createQuery("SELECT COUNT(u) FROM Usuario u", Long.class).getSingleResult();
        if (usuariosExistentes > 0) {
            System.out.println("[ReVuelta] La base de datos ya tiene informacion: no se cargan datos de prueba.");
            return;
        }

        List<Usuario> usuarios = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();
        List<EstadoPrenda> estados = new ArrayList<>();
        List<Prenda> prendas = new ArrayList<>();
        List<Campana> campanas = new ArrayList<>();
        List<PuntoAcopio> puntos = new ArrayList<>();
        List<Transportista> transportistas = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            usuarios.add(usuario("Usuario de prueba " + i, "usuario" + i + "@revuelta.test",
                i == 1 ? "docente" : "estudiante", i % 2 == 0 ? "#0D9488" : "#1D4ED8"));
            categorias.add(categoria("Categoria " + i, "Categoria de prueba " + i, "categoria-" + i,
                "icono-" + i, i));
            estados.add(estadoPrenda("Estado " + i, "Estado de prueba " + i, (i - 1) % 5 + 1,
                i % 2 == 0 ? "#0EA5E9" : "#0D9488", i % 3 == 0, true));
        }

        for (int i = 0; i < 20; i++) {
            Prenda prendaCreada = prenda("Prenda de prueba " + (i + 1), "Descripcion de la prenda de prueba " + (i + 1),
                i % 2 == 0 ? "M" : "L", 30000.0 + i * 5000.0, estados.get(i), categorias.get(i), usuarios.get((i + 1) % 20));
            prendas.add(prendaCreada);
            imagenPrenda("https://ejemplo.com/prendas/prueba-" + (i + 1) + ".jpg", true, 1, "jpg", 100 + i,
                prendaCreada);
            campanas.add(campana("Campana de prueba " + (i + 1), i));
            puntos.add(puntoAcopio("Punto de acopio " + (i + 1), "Carrera " + (40 + i) + " # 10-20", "Medellin",
                "Lunes a viernes 8:00 - 18:00", 100 + i * 5));
            transportistas.add(transportista("Transportista de prueba " + (i + 1), i));
        }

        for (int i = 0; i < 20; i++) {
            Pedido pedidoCreado = pedido(usuarios.get(i), prendas.get(i), i);
            detallePedido(pedidoCreado, prendas.get(i), i);
            transaccion(pedidoCreado, i);
            em.persist(trueque(prendas.get(i), prendas.get((i + 1) % 20), usuarios.get(i), i));
            Envio envioCreado = envio(pedidoCreado, transportistas.get(i), puntos.get(i), i);
            seguimiento(envioCreado, "EN_TRANSITO", "Seguimiento de prueba " + (i + 1), "Medellin",
                LocalDateTime.now().minusHours(i), 6.20 + i * 0.001, -75.57 - i * 0.001);
            cupon("PRUEBA" + String.format("%02d", i + 1), "PORCENTAJE", String.valueOf(5 + i),
                100, i, campanas.get(i));
            recompensa("Recompensa de prueba " + (i + 1), 50 + i * 10,
                "Recompensa de prueba para usuarios", 20 + i, i % 2 == 0 ? "DESCUENTO" : "ENVIO_GRATIS");
            Resena resenaCreada = resena(usuarios.get(i), usuarios.get((i + 1) % 20), i);
            calificacion(resenaCreada, i % 5 + 1, "CALIDAD", "Calificacion de prueba " + (i + 1), true, 1);
            reporte(usuarios.get(i), prendas.get(i), i);
        }

        System.out.println("""

            ================================================================
             [ReVuelta] Datos de prueba cargados correctamente.
             Swagger  -> http://localhost:8080/swagger-ui.html
             Consola  -> http://localhost:8080/h2-console
                         JDBC URL: jdbc:h2:file:./data/revuelta
                         Usuario : sa   (sin contrasena)
             Login de ejemplo: usuario1@revuelta.test / Revuelta2026
            ================================================================
            """);
    }

    // ===== Metodos de apoyo: uno por tabla, para no repetir codigo =====

    private Usuario usuario(String nombre, String correo, String rol, String color) {
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setContrasena_hash(codificador.encode("Revuelta2026"));
        u.setRol(rol);
        u.setActivo(true);
        u.setColor_avatar(color);
        u.setFecha_registro(LocalDateTime.now());
        em.persist(u);
        return u;
    }

    private Categoria categoria(String nombre, String descripcion, String slug, String icono, Integer orden) {
        Categoria c = new Categoria();
        c.setNombre(nombre);
        c.setDescripcion(descripcion);
        c.setSlug(slug);
        c.setIcono(icono);
        c.setActiva(true);
        c.setOrden(orden);
        em.persist(c);
        return c;
    }

    private EstadoPrenda estadoPrenda(String nombre, String descripcion, Integer nivel, String color,
                                      Boolean requiereRevision, Boolean activo) {
        EstadoPrenda e = new EstadoPrenda();
        e.setNombre(nombre);
        e.setDescripcion(descripcion);
        e.setNivelDesgaste(nivel);
        e.setColorEtiqueta(color);
        e.setRequiereRevision(requiereRevision);
        e.setActivo(activo);
        em.persist(e);
        return e;
    }

    private Prenda prenda(String titulo, String descripcion, String talla, double precio,
                          EstadoPrenda estado, Categoria categoria, Usuario vendedor) {
        Prenda p = new Prenda();
        p.setTitulo(titulo);
        p.setDescripcion(descripcion);
        p.setTalla(talla);
        p.setPrecio(precio);
        p.setFecha_publicacion(LocalDate.now().minusDays(7));
        p.setDisponible(true);
        p.setEstado(estado);
        p.setCategoria(categoria);
        p.setUsuario(vendedor);
        em.persist(p);
        return p;
    }

    private void imagenPrenda(String url, Boolean principal, Integer orden, String formato,
                              Integer tamanoKb, Prenda prenda) {
        ImagenPrenda i = new ImagenPrenda();
        i.setUrl(url);
        i.setEs_Principal(principal);
        i.setOrden(orden);
        i.setFormato(formato);
        i.setTamano_KB(tamanoKb);
        i.setFecha_subida(LocalDate.now().minusDays(7));
        i.setPrenda(prenda);
        em.persist(i);
    }

    private Campana campana(String nombre, int indice) {
        Campana c = new Campana();
        c.setNombre_campana(nombre);
        c.setDescripcion_campana("Descuentos de prueba");
        c.setFecha_inicio(LocalDateTime.now().minusDays(indice + 1));
        c.setFecha_final(LocalDateTime.now().plusDays(30));
        c.setDescuento_pct(5.0 + indice);
        c.setActiva(true);
        em.persist(c);
        return c;
    }

    private Transportista transportista(String nombre, int indice) {
        Transportista t = new Transportista();
        t.setNombre(nombre);
        t.setTipo_vehiculo(indice % 2 == 0 ? "Motocicleta" : "Automovil");
        t.setPlaca(String.format("P%02dT%03d", indice / 100, indice + 1));
        t.setTelefono("300000" + String.format("%04d", indice + 1));
        t.setZona_cobertura("Valle de Aburra");
        t.setDisponible(true);
        em.persist(t);
        return t;
    }

    private Pedido pedido(Usuario usuario, Prenda prenda, int indice) {
        Pedido p = new Pedido();
        p.setFecha(LocalDate.now().minusDays(indice + 1));
        p.setEstado(indice % 2 == 0 ? "CONFIRMADO" : "ENTREGADO");
        p.setTotal(prenda.getPrecio());
        p.setMetodo_pago(indice % 2 == 0 ? "TARJETA" : "TRANSFERENCIA");
        p.setDireccion_entrega("Calle " + (10 + indice) + " # 20-30, Medellin");
        p.setNotas("Pedido de prueba " + (indice + 1));
        p.setUsuario(usuario);
        em.persist(p);
        return p;
    }

    private void detallePedido(Pedido pedido, Prenda prenda, int indice) {
        DetallePedido d = new DetallePedido();
        d.setCantidad(1);
        d.setPrecio_unitario(prenda.getPrecio());
        d.setDescuento(0.0);
        d.setSubtotal(prenda.getPrecio());
        d.setEstado_item("CONFIRMADO");
        d.setFecha(pedido.getFecha());
        d.setPedido(pedido);
        d.setPrenda(prenda);
        em.persist(d);
    }

    private void transaccion(Pedido pedido, int indice) {
        Transaccion t = new Transaccion();
        t.setTipo("PAGO");
        t.setMonto(pedido.getTotal());
        t.setEstado("APROBADA");
        t.setReferencia_pago("REF-PRUEBA-" + String.format("%02d", indice + 1));
        t.setFecha(pedido.getFecha());
        t.setComprobante("comprobante-prueba-" + (indice + 1) + ".pdf");
        t.setPedido(pedido);
        em.persist(t);
    }

    private Trueque trueque(Prenda ofrecida, Prenda deseada, Usuario proponente, int indice) {
        Trueque t = new Trueque();
        t.setEstado(indice % 2 == 0 ? "PENDIENTE" : "ACEPTADO");
        t.setFecha_propuesta(LocalDate.now().minusDays(indice + 1));
        t.setFecha_respuesta(indice % 2 == 0 ? null : LocalDate.now().minusDays(indice));
        t.setMensaje("Propuesta de trueque de prueba " + (indice + 1));
        t.setValor_estimado(ofrecida.getPrecio());
        t.setAceptado(indice % 2 != 0);
        t.setPrenda(ofrecida);
        t.setPrendaDeseada(deseada);
        t.setProponente(proponente);
        return t;
    }

    private Envio envio(Pedido pedido, Transportista transportista, PuntoAcopio punto, int indice) {
        Envio e = new Envio();
        e.setCodigo_guia("GUIA-PRUEBA-" + String.format("%02d", indice + 1));
        e.setEstado("EN_TRANSITO");
        e.setCosto(10000.0 + indice * 100.0);
        e.setFecha_despacho(pedido.getFecha().plusDays(1));
        e.setFecha_entrega_estimada(LocalDate.now().plusDays(2));
        e.setPeso_kg(1.0 + indice * 0.1);
        e.setPedido(pedido);
        e.setTransportista(transportista);
        e.setPuntos_de_acopio(punto);
        em.persist(e);
        return e;
    }

    private PuntoAcopio puntoAcopio(String nombre, String direccion, String ciudad, String horario, Integer capacidad) {
        PuntoAcopio p = new PuntoAcopio();
        p.setNombre(nombre);
        p.setDireccion(direccion);
        p.setCiudad(ciudad);
        p.setHorario(horario);
        p.setCapacidad(capacidad);
        p.setActivo(true);
        em.persist(p);
        return p;
    }

    private void seguimiento(Envio envio, String estado, String descripcion, String ubicacion,
                             LocalDateTime fechaHora, Double lat, Double lon) {
        SeguimientoEnvio s = new SeguimientoEnvio();
        s.setEstado(estado);
        s.setDescripcion(descripcion);
        s.setUbicacion(ubicacion);
        s.setFecha_hora(fechaHora);
        s.setLatitud(lat);
        s.setLongitud(lon);
        s.setEnvio(envio);
        em.persist(s);
    }

    private void cupon(String codigo, String tipo, String valor, Integer maximos, Integer actuales, Campana campana) {
        Cupon c = new Cupon();
        c.setCodigo(codigo);
        c.setTipo(tipo);
        c.setValor(valor);
        c.setUsos_maximos(maximos);
        c.setUsos_actuales(actuales);
        c.setFecha_expiracion(LocalDateTime.now().plusDays(25));
        c.setCampana(campana);
        em.persist(c);
    }

    private void recompensa(String nombre, Integer puntos, String descripcion, Integer stock, String tipo) {
        Recompensa r = new Recompensa();
        r.setNombre(nombre);
        r.setPuntos_requeridos(puntos);
        r.setDescripcion(descripcion);
        r.setStock(stock);
        r.setTipo(tipo);
        r.setActiva(true);
        em.persist(r);
    }

    private void calificacion(Resena resena, Integer puntaje, String dimension, String comentario,
                              Boolean verificada, Integer peso) {
        Calificacion c = new Calificacion();
        c.setPuntaje(puntaje);
        c.setDimension(dimension);
        c.setComentario_corto(comentario);
        c.setFecha(LocalDate.now().minusDays(1));
        c.setVerificada(verificada);
        c.setPeso(peso);
        c.setResena(resena);
        em.persist(c);
    }

    private Resena resena(Usuario autor, Usuario resenado, int indice) {
        Resena r = new Resena();
        r.setTitulo("Resena de prueba " + (indice + 1));
        r.setComentario("Comentario de prueba sobre la experiencia de intercambio.");
        r.setFecha(LocalDate.now().minusDays(indice + 1));
        r.setRecomendado(indice % 2 == 0);
        r.setEditada(false);
        r.setVisible(true);
        r.setAutor(autor);
        r.setUsuarioResenado(resenado);
        em.persist(r);
        return r;
    }

    private void reporte(Usuario usuario, Prenda prenda, int indice) {
        Reporte r = new Reporte();
        r.setMotivo("DESCRIPCION_ENGANOSA");
        r.setDescripcion("Reporte de prueba " + (indice + 1));
        r.setEstado(indice % 2 == 0 ? "ABIERTO" : "CERRADO");
        r.setPrioridad(indice % 3 == 0 ? "ALTA" : "MEDIA");
        r.setFecha(LocalDate.now().minusDays(indice));
        r.setResuelto(indice % 2 != 0);
        r.setUsuario(usuario);
        r.setPrenda(prenda);
        em.persist(r);
    }
}
