package com.example.ReVueltaBack.config;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
import com.example.ReVueltaBack.modelos.Reseña;
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

        // ---------- Usuarios (modulo comun de Autenticacion) ----------
        Usuario docente = usuario("Juan José Gallego Mesa", "juan.gallego@cesde.edu.co", "docente", "#1D4ED8");
        Usuario vendedora = usuario("Laura Restrepo", "laura.restrepo@correo.com", "estudiante", "#0EA5E9");
        Usuario comprador = usuario("Carlos Muñoz", "carlos.munoz@correo.com", "estudiante", "#0D9488");

        // ---------- Catalogo ----------
        Categoria chaquetas = categoria("Chaquetas", "Chaquetas y abrigos de segunda mano", "chaquetas", "jacket", 1);
        Categoria jeans = categoria("Jeans", "Pantalones de mezclilla en buen estado", "jeans", "pants", 2);
        Categoria camisas = categoria("Camisas", "Camisas y blusas para toda ocasion", "camisas", "shirt", 3);

        EstadoPrenda comoNuevo = estadoPrenda("Como nuevo", "Sin señales de uso", 1, "#0D9488", false, true);
        EstadoPrenda buenEstado = estadoPrenda("Buen estado", "Uso leve, sin daños", 2, "#0EA5E9", false, true);
        EstadoPrenda conDetalles = estadoPrenda("Con detalles", "Presenta desgaste visible", 4, "#D97706", true, true);

        Prenda chaquetaJean = prenda("Chaqueta de jean clasica", "Chaqueta azul talla M, marca reconocida",
                "M", 85000.0, comoNuevo, chaquetas, vendedora);
        Prenda jeanNegro = prenda("Jean negro slim fit", "Jean negro talla 30, poco uso",
                "30", 60000.0, buenEstado, jeans, vendedora);
        Prenda camisaLino = prenda("Camisa de lino blanca", "Camisa de lino talla L, ideal para clima calido",
                "L", 45000.0, conDetalles, camisas, comprador);

        imagenPrenda("https://ejemplo.com/prendas/chaqueta-jean-1.jpg", true, 1, "jpg", 240, chaquetaJean);
        imagenPrenda("https://ejemplo.com/prendas/chaqueta-jean-2.jpg", false, 2, "jpg", 180, chaquetaJean);
        imagenPrenda("https://ejemplo.com/prendas/jean-negro-1.jpg", true, 1, "png", 310, jeanNegro);

        // ---------- Marketplace ----------
        Pedido pedido = new Pedido();
        pedido.setFecha(LocalDate.now().minusDays(3));
        pedido.setEstado("CONFIRMADO");
        pedido.setTotal(145000.0);
        pedido.setMetodo_pago("TARJETA");
        pedido.setDireccion_entrega("Calle 50 # 40-20, Medellin");
        pedido.setNotas("Entregar en horario de oficina");
        pedido.setUsuario(comprador);
        em.persist(pedido);

        DetallePedido detalle = new DetallePedido();
        detalle.setCantidad(1);
        detalle.setPrecio_unitario(85000.0);
        detalle.setDescuento(0.0);
        detalle.setSubtotal(85000.0);
        detalle.setEstado_item("CONFIRMADO");
        detalle.setFecha(LocalDate.now().minusDays(3));
        detalle.setPedido(pedido);
        detalle.setPrenda(chaquetaJean);
        em.persist(detalle);

        Transaccion transaccion = new Transaccion();
        transaccion.setTipo("PAGO");
        transaccion.setMonto(145000.0);
        transaccion.setEstado("APROBADA");
        transaccion.setReferencia_pago("REF-2026-000001");
        transaccion.setFecha(LocalDate.now().minusDays(3));
        transaccion.setComprobante("comprobante-000001.pdf");
        transaccion.setPedido(pedido);
        em.persist(transaccion);

        Trueque trueque = new Trueque();
        trueque.setEstado("PROPUESTO");
        trueque.setFecha_propuesta(LocalDate.now().minusDays(1));
        trueque.setMensaje("Te cambio mi camisa de lino por tu jean negro");
        trueque.setValor_estimado(50000.0);
        trueque.setAceptado(false);
        trueque.setPrenda(camisaLino);
        trueque.setPrendaDeseada(jeanNegro);
        trueque.setProponente(comprador);
        em.persist(trueque);

        // ---------- Logistica ----------
        Transportista transportista = new Transportista();
        transportista.setNombre("Envios Rapidos SAS");
        transportista.setTipo_vehiculo("Motocicleta");
        transportista.setPlaca("ABC12D");
        transportista.setTelefono("3001234567");
        transportista.setZona_cobertura("Valle de Aburra");
        transportista.setDisponible(true);
        em.persist(transportista);

        PuntoAcopio acopioCentro = puntoAcopio("Acopio Centro", "Carrera 45 # 52-10", "Medellin",
                "Lunes a viernes 8:00 - 18:00", 200);
        puntoAcopio("Acopio Sur", "Calle 10 # 30-45", "Envigado", "Lunes a sabado 9:00 - 17:00", 120);

        Envio envio = new Envio();
        envio.setCodigo_guia("GUIA-000001");
        envio.setEstado("EN_TRANSITO");
        envio.setCosto(12000.0);
        envio.setFecha_despacho(LocalDate.now().minusDays(2));
        envio.setFecha_entrega_estimada(LocalDate.now().plusDays(1));
        envio.setPeso_kg(1.2);
        envio.setPedido(pedido);
        envio.setTransportista(transportista);
        envio.setPuntos_de_acopio(acopioCentro);
        em.persist(envio);

        seguimiento(envio, "RECIBIDO", "Paquete recibido en el punto de acopio", "Acopio Centro",
                LocalDateTime.now().minusDays(2), 6.2442, -75.5812);
        seguimiento(envio, "EN_TRANSITO", "En ruta hacia la direccion del comprador", "Medellin - Poblado",
                LocalDateTime.now().minusHours(5), 6.2088, -75.5673);

        // ---------- Mercadeo ----------
        Campana campana = new Campana();
        campana.setNombre_campana("Regreso a clases");
        campana.setDescripcion_campana("Descuentos en prendas casuales");
        campana.setFecha_inicio(LocalDateTime.now().minusDays(5));
        campana.setFecha_final(LocalDateTime.now().plusDays(25));
        campana.setDescuento_pct(15.0);
        campana.setActiva(true);
        em.persist(campana);

        cupon("REVUELTA15", "PORCENTAJE", "15", 100, 3, campana);
        cupon("ENVIOGRATIS", "FIJO", "12000", 50, 0, campana);

        recompensa("Bono de envio gratis", 100, "Un envio sin costo dentro del Valle de Aburra", 30, "ENVIO");
        recompensa("Cupon de 20.000", 250, "Descuento de 20.000 pesos en tu proxima compra", 15, "DESCUENTO");

        // ---------- Comunidad ----------
        Reseña reseña = new Reseña();
        reseña.setTitulo("Excelente vendedora");
        reseña.setComentario("La prenda llego tal como se describia y el envio fue muy rapido.");
        reseña.setFecha(LocalDate.now().minusDays(1));
        reseña.setRecomendado(true);
        reseña.setEditada(false);
        reseña.setVisible(true);
        reseña.setAutor(comprador);
        reseña.setUsuarioReseñado(vendedora);
        em.persist(reseña);

        calificacion(reseña, 5, "PUNTUALIDAD", "Entrego antes de lo prometido", true, 1);
        calificacion(reseña, 4, "CALIDAD", "La prenda estaba en muy buen estado", true, 1);

        Reporte reporte = new Reporte();
        reporte.setMotivo("DESCRIPCION_ENGANOSA");
        reporte.setDescripcion("La talla publicada no coincide con la prenda recibida");
        reporte.setEstado("ABIERTO");
        reporte.setPrioridad("MEDIA");
        reporte.setFecha(LocalDate.now());
        reporte.setResuelto(false);
        reporte.setUsuario(docente);
        reporte.setPrenda(camisaLino);
        em.persist(reporte);

        System.out.println("""

            ================================================================
             [ReVuelta] Datos de prueba cargados correctamente.
             Swagger  -> http://localhost:8080/swagger-ui.html
             Consola  -> http://localhost:8080/h2-console
                         JDBC URL: jdbc:h2:file:./data/revuelta
                         Usuario : sa   (sin contrasena)
             Login de ejemplo: juan.gallego@cesde.edu.co / Revuelta2026
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
        i.setTamaño_KB(tamanoKb);
        i.setFecha_subida(LocalDate.now().minusDays(7));
        i.setPrenda(prenda);
        em.persist(i);
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

    private void calificacion(Reseña reseña, Integer puntaje, String dimension, String comentario,
                              Boolean verificada, Integer peso) {
        Calificacion c = new Calificacion();
        c.setPuntaje(puntaje);
        c.setDimension(dimension);
        c.setComentario_corto(comentario);
        c.setFecha(LocalDate.now().minusDays(1));
        c.setVerificada(verificada);
        c.setPeso(peso);
        c.setReseña(reseña);
        em.persist(c);
    }
}
