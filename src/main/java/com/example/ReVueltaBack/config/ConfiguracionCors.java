package com.example.ReVueltaBack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuracion de CORS (Cross-Origin Resource Sharing).
 *
 * QUE PROBLEMA RESUELVE
 * Por seguridad, el navegador bloquea que una pagina servida desde un origen
 * (por ejemplo el React en http://localhost:5173) llame a una API que vive en
 * otro origen distinto (esta API, en http://localhost:8080). Sin esta clase el
 * front recibe el error:
 *
 *   "has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header"
 *
 * Ojo: Postman, Thunder Client, curl y el propio Swagger NO se ven afectados,
 * porque la restriccion la aplica el navegador, no el servidor. Por eso la API
 * puede "funcionar en Postman" y fallar en React al mismo tiempo.
 *
 * COMO ESTA CONFIGURADA AQUI
 * Completamente abierta: cualquier origen, cualquier metodo y cualquier
 * cabecera. Es una decision deliberada porque este es un proyecto de clase y
 * cada estudiante levanta su front en un puerto distinto.
 *
 * EN PRODUCCION ESTO NO SE HACE ASI. Lo correcto es listar unicamente los
 * dominios propios, por ejemplo:
 *
 *   .allowedOrigins("https://revuelta.com", "https://admin.revuelta.com")
 *
 * Se usa allowedOriginPatterns en vez de allowedOrigins porque el comodin "*"
 * de allowedOrigins es incompatible con allowCredentials(true); con patterns
 * la configuracion sigue funcionando si mas adelante se agregan cookies o
 * sesiones al proyecto.
 */
@Configuration
public class ConfiguracionCors implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registro) {
        registro.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD")
                .allowedHeaders("*")
                // Deja que el front lea cabeceras de la respuesta (p. ej. Location o
                // un futuro Authorization); sin esto solo ve las basicas.
                .exposedHeaders("*")
                // El navegador guarda el permiso 1 hora y deja de mandar la peticion
                // OPTIONS previa en cada llamada.
                .maxAge(3600);
    }
}
