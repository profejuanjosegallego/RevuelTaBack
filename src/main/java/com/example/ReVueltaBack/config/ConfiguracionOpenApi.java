package com.example.ReVueltaBack.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Portada de la documentacion interactiva de la API.
 *
 * Con la aplicacion corriendo:
 *   - Interfaz Swagger : http://localhost:8080/swagger-ui.html
 *   - JSON OpenAPI     : http://localhost:8080/v3/api-docs
 *   - Consola de la BD : http://localhost:8080/h2-console
 */
@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API ReVuelta",
        version = "1.0",
        description = """
            Plataforma de compra, venta y trueque de ropa de segunda mano.
            Proyecto integrador de Backend 2 — 18 tablas repartidas en 5 equipos celula:
            Catalogo, Marketplace, Logistica, Mercadeo y Comunidad.
            """,
        contact = @Contact(name = "Prof. Juan José Gallego Mesa, M.Sc.")
    ),
    servers = @Server(url = "http://localhost:8080", description = "Entorno local de desarrollo")
)
public class ConfiguracionOpenApi {
}
