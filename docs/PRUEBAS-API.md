# Pruebas de la API ReVuelta — cuerpos JSON

Guía de cuerpos JSON listos para copiar y pegar, uno por endpoint.
Sirve para **Swagger UI, Thunder Client, Postman, Insomnia o curl** — los JSON son los mismos, solo cambia dónde se pegan.

Generada a partir de los `RequestDTO`, controladores y validaciones reales del proyecto.

---

## 1. Antes de empezar

**Arrancar el backend** (desde `RevuelTaBack/`, en Git Bash):

```bash
export JAVA_HOME=/c/Users/jjose/.jdks/openjdk-25.0.1
./mvnw -o dependency:build-classpath -Dmdep.outputFile=cp.txt
java -cp "target/classes;$(cat cp.txt)" com.example.ReVueltaBack.ReVueltaBackApplication
```

O desde el IDE, ejecutando `ReVueltaBackApplication`.

| Recurso | URL |
|---|---|
| API | `http://localhost:8080` |
| Swagger | `http://localhost:8080/swagger-ui.html` |
| Consola H2 | `http://localhost:8080/h2-console` — JDBC `jdbc:h2:file:./data/revuelta`, usuario `sa`, sin contraseña |

**No hay login ni token.** La API todavía no tiene seguridad, así que ninguna petición lleva header `Authorization`.

### Dónde se pega el JSON según la herramienta

| Herramienta | Dónde |
|---|---|
| **Swagger UI** | Abrir el endpoint → botón **Try it out** → se habilita el recuadro *Request body* → borrar el ejemplo y pegar el JSON → **Execute** |
| **Thunder Client** (VS Code) | New Request → método y URL → pestaña **Body** → **Json** |
| **Postman** | Método y URL → pestaña **Body** → **raw** → tipo **JSON** |
| **Insomnia** | Método y URL → **Body** → **JSON** |
| **curl** | `curl -X POST http://localhost:8080/api/... -H "Content-Type: application/json" --data-binary "@cuerpo.json"` |

En Swagger, Thunder Client, Postman e Insomnia el `Content-Type: application/json` se pone solo. En curl hay que escribirlo.

### Los marcadores `<ID_ALGO>`

Casi todas las tablas dependen de otras, así que los JSON traen marcadores así:

```json
"idVendedor": "<ID_USUARIO>"
```

**Hay que reemplazarlos por un UUID real**, dejando las comillas:

```json
"idVendedor": "8c3f1a92-4d6b-4e11-9a77-2b5c0e8d41f9"
```

Si se deja el marcador sin reemplazar, la respuesta es **400** (no es un UUID válido). Eso es normal y sirve como aviso.

**De dónde salen esos UUID:** de un `GET` al listado correspondiente. Cada objeto de la respuesta trae su campo `id`; se copia ese valor.

| Marcador | Se saca de |
|---|---|
| `<ID_USUARIO>`, `<ID_USUARIO_2>` | `GET /api/usuarios` |
| `<ID_CATEGORIA>` | `GET /api/categorias` |
| `<ID_ESTADO_PRENDA>` | `GET /api/estados-prenda` |
| `<ID_PRENDA>`, `<ID_PRENDA_2>` | `GET /api/prendas` |
| `<ID_PEDIDO>` | `GET /api/pedidos` |
| `<ID_ENVIO>` | `GET /api/envios` |
| `<ID_CAMPANA>` | `GET /api/campanas` |
| `<ID_RESENA>` | `GET /api/resenas` |
| `<ID_TRANSPORTISTA>`, `<ID_PUNTO_ACOPIO>` | ⚠️ No hay endpoint todavía → sacarlos de la consola H2 (ver sección 18) |

> **Atajo opcional:** Postman y Thunder Client permiten guardar variables y escribirlas como `{{idUsuario}}`. Es cómodo cuando ya se domina el flujo, pero **no funciona en Swagger UI** (ahí el texto se envía literal). Por eso esta guía usa UUIDs pegados a mano, que funcionan en todas las herramientas.

### Ojo con las tildes y las eñes

**Ningún nombre de campo lleva eñe ni tildes.** Se normalizaron todos a ASCII (`idUsuarioResenado`, `idResena`), igual que las rutas (`/api/resenas`). Si viene de una colección vieja con `idUsuarioReseñado` o `reseñaID`, hay que actualizarla.

Los **valores** sí pueden llevar tildes y eñes ("Medellín", "algodón"). En Swagger, Thunder Client y Postman no hay problema (mandan UTF-8). Con **curl en Git Bash** el cuerpo debe ir en archivo: `--data-binary "@cuerpo.json"`; escrito directo en la línea de comandos se manda en la codificación de Windows y da **400 "Invalid UTF-8"**.

---

## 2. Datos que ya existen al arrancar

`CargaDatosIniciales` (en `src/main/java/com/example/ReVueltaBack/config/`) siembra la base **solo si está vacía**. Ya hay 3 usuarios, 3 categorías, 3 estados de prenda, 3 prendas, 1 pedido, 1 envío, 1 campaña, 2 cupones, etc.

Para empezar de cero: apagar la app y borrar la carpeta `RevuelTaBack/data/`.

Usuario de ejemplo: `juan.gallego@cesde.edu.co` / `Revuelta2026`.

**Primer paso recomendado:** hacer los `GET` de listado para copiar UUIDs reales.

```
GET http://localhost:8080/api/usuarios
GET http://localhost:8080/api/categorias
GET http://localhost:8080/api/estados-prenda
GET http://localhost:8080/api/prendas
GET http://localhost:8080/api/pedidos
GET http://localhost:8080/api/campanas
```

---

## 3. Orden recomendado de las pruebas

Cada nivel necesita los IDs del anterior:

1. **Base:** Usuarios → Categorías → Estados de prenda
2. **Catálogo:** Prendas → Imágenes de prenda
3. **Marketplace:** Pedidos → Transacciones → Trueques
4. **Logística:** Envíos → Seguimiento de envío
5. **Mercadeo:** Campañas → Cupones → Recompensas
6. **Comunidad:** Reseñas → Calificaciones → Reportes

---

## 4. Usuarios — `/api/usuarios`

| Método | Ruta |
|---|---|
| POST | `/api/usuarios` |
| GET | `/api/usuarios` |
| GET | `/api/usuarios/buscar?correo=...` |
| GET | `/api/usuarios/{id}` |
| PUT | `/api/usuarios/{id}` |
| PATCH | `/api/usuarios/{id}/contrasena?contrasenaNueva=...` |
| DELETE | `/api/usuarios/{id}` |

### POST — crear usuario

```json
{
  "nombre": "Mariana Ospina",
  "correo": "mariana.ospina@correo.com",
  "contrasena": "Revuelta2026",
  "rol": "estudiante",
  "activo": true,
  "colorAvatar": "#1D4ED8"
}
```

### PUT — actualizar usuario

```json
{
  "nombre": "Mariana Ospina Vélez",
  "correo": "mariana.ospina@correo.com",
  "contrasena": "Revuelta2026",
  "rol": "docente",
  "activo": true,
  "colorAvatar": "#0EA5E9"
}
```

### PATCH — cambiar contraseña

**No lleva cuerpo JSON.** La contraseña va como parámetro en la URL:

```
PATCH http://localhost:8080/api/usuarios/<ID_USUARIO>/contrasena?contrasenaNueva=MiClaveSegura123
```

En Swagger aparece como un campo de texto llamado `contrasenaNueva`, no como *Request body*.

### Reglas

- `contrasena` viaja en **texto plano**; el servicio la cifra con BCrypt. La respuesta **nunca** trae el hash.
- Mínimo **8 caracteres**.
- `correo` debe cumplir `algo@dominio.com` y es **único**.
- `activo` se asume `true` si se omite.

### Pruebas negativas

| Qué se manda | Esperado |
|---|---|
| Correo repetido (`juan.gallego@cesde.edu.co`) | **409 Conflict** |
| `"correo": "sinarroba"` | **400** correo no válido |
| `"contrasena": "1234"` | **400** mínimo 8 caracteres |
| `"nombre": ""` | **400** nombre obligatorio |
| `GET /api/usuarios/{uuid-inventado}` | **404** |

---

## 5. Categorías — `/api/categorias`

### POST

```json
{
  "nombre": "Vestidos",
  "descripcion": "Vestidos casuales y de fiesta de segunda mano",
  "slug": "vestidos",
  "icono": "dress",
  "activa": true,
  "orden": 4
}
```

### PUT

```json
{
  "nombre": "Vestidos y faldas",
  "descripcion": "Vestidos, faldas y enterizos en buen estado",
  "slug": "vestidos-y-faldas",
  "icono": "dress",
  "activa": true,
  "orden": 4
}
```

### Reglas

- `nombre` obligatorio.
- `descripcion` entre **3 y 255** caracteres.
- `slug` en minúsculas, sin tildes ni espacios, separado por guiones: `^[a-z0-9]+(-[a-z0-9]+)*$`.

### Pruebas negativas

| Qué se manda | Esperado |
|---|---|
| `"slug": "Vestidos Bonitos"` | **400** (mayúsculas y espacio) |
| `"descripcion": "ab"` | **400** |
| `"nombre": ""` | **400** |

---

## 6. Estados de prenda — `/api/estados-prenda`

Rutas extra: `GET /api/estados-prenda/requieren-revision` y `GET /api/estados-prenda/buscar?texto=nuevo`.

### POST

```json
{
  "nombre": "Con reparación menor",
  "descripcion": "Requiere un arreglo pequeño antes de publicarse",
  "nivelDesgaste": 3,
  "colorEtiqueta": "#D97706",
  "requiereRevision": true,
  "activo": true
}
```

### PUT

```json
{
  "nombre": "Con reparación menor",
  "descripcion": "Botón o costura suelta, se arregla en minutos",
  "nivelDesgaste": 2,
  "colorEtiqueta": "#0EA5E9",
  "requiereRevision": false,
  "activo": true
}
```

### Reglas

- `nombre` obligatorio.
- `descripcion` entre **3 y 255**.
- `nivelDesgaste` entre **0 y 5**.
- Si se omiten, `requiereRevision` = `false` y `activo` = `true`.

### Prueba negativa

`"nivelDesgaste": 9` → **400**.

---

## 7. Prendas — `/api/prendas`

Necesita: `idCategoria`, `idEstado`, `idVendedor` (un usuario).

### POST

```json
{
  "titulo": "Buzo oversize gris",
  "descripcion": "Buzo de algodón talla L, usado dos veces",
  "talla": "L",
  "precio": 55000.0,
  "fechaPublicacion": "2026-08-20",
  "disponible": true,
  "idCategoria": "<ID_CATEGORIA>",
  "idEstado": "<ID_ESTADO_PRENDA>",
  "idVendedor": "<ID_USUARIO>"
}
```

### PUT

```json
{
  "titulo": "Buzo oversize gris — rebajado",
  "descripcion": "Buzo de algodón talla L, usado dos veces. Precio negociable",
  "talla": "L",
  "precio": 45000.0,
  "fechaPublicacion": "2026-08-20",
  "disponible": true,
  "idCategoria": "<ID_CATEGORIA>",
  "idEstado": "<ID_ESTADO_PRENDA>",
  "idVendedor": "<ID_USUARIO>"
}
```

### Reglas

- `titulo` obligatorio.
- `descripcion` entre **3 y 255**.
- `precio` **mayor que 0** (es `Double`, va con decimales).
- Los tres UUID deben existir realmente.

### Pruebas negativas

| Qué se manda | Esperado |
|---|---|
| `"precio": 0` | **400** |
| `"precio": -1000.0` | **400** |
| `"idCategoria"` inventado | **404 / 500** (revisar en clase cómo lo maneja el servicio) |

---

## 8. Imágenes de prenda — `/api/imagenes-prenda`

### POST

```json
{
  "url": "https://ejemplo.com/prendas/buzo-gris-1.jpg",
  "esPrincipal": true,
  "orden": 1,
  "formato": "jpg",
  "tamanoKb": 320,
  "prendaId": "<ID_PRENDA>"
}
```

### PUT

```json
{
  "url": "https://ejemplo.com/prendas/buzo-gris-2.png",
  "esPrincipal": false,
  "orden": 2,
  "formato": "png",
  "tamanoKb": 410,
  "prendaId": "<ID_PRENDA>"
}
```

### Reglas

- `url` obligatoria.
- `formato` solo **jpg, jpeg, png, webp**.
- `tamanoKb` mayor que 0 y máximo **5120** (5 MB).

### Pruebas negativas

| Qué se manda | Esperado |
|---|---|
| `"formato": "gif"` | **400** formato no soportado |
| `"tamanoKb": 0` | **400** |
| `"tamanoKb": 99999` | **400** supera 5120 KB |

---

## 9. Pedidos — `/api/pedidos`

### POST

```json
{
  "fecha": "2026-08-20",
  "estado": "PENDIENTE",
  "total": 100000.0,
  "metodoPago": "TARJETA",
  "direccionEntrega": "Calle 50 # 40-20, Medellín",
  "notas": "Entregar en horario de oficina",
  "idComprador": "<ID_USUARIO>"
}
```

> **Detalle para discutir en clase:** `PedidoRequestDTO.toEntity()` **ignora** la `fecha` que se envía y siempre pone `LocalDate.now()`. Es un bug menor del equipo de pedidos.

### PUT

```json
{
  "fecha": "2026-08-20",
  "estado": "CONFIRMADO",
  "total": 120000.0,
  "metodoPago": "EFECTIVO",
  "direccionEntrega": "Carrera 43A # 1-50, Medellín",
  "notas": "Cambio de dirección solicitado por el comprador",
  "idComprador": "<ID_USUARIO>"
}
```

### Reglas

- `direccionEntrega` obligatoria.
- `total` **mayor que 0**.
- `notas` es opcional, pero si viene debe medir entre **3 y 255**.
- `estado` y `metodoPago` **no se validan** (se acepta cualquier texto) — punto de mejora del equipo.

### Pruebas negativas

| Qué se manda | Esperado |
|---|---|
| `"total": 0` | **400** |
| `"direccionEntrega": ""` | **400** |
| `"notas": "ab"` | **400** |

---

## 10. Transacciones — `/api/transacciones`

Necesita un `idPedido` existente.

### POST

```json
{
  "tipo": "PAGO",
  "monto": 100000.0,
  "estado": "APROBADA",
  "referenciaPago": "REF-2026-000123",
  "fecha": "2026-08-20",
  "comprobante": "comprobante-000123.pdf",
  "idPedido": "<ID_PEDIDO>"
}
```

### PUT

```json
{
  "tipo": "REEMBOLSO",
  "monto": 100000.0,
  "estado": "PENDIENTE",
  "referenciaPago": "REF-2026-000123-R",
  "fecha": "2026-08-20",
  "comprobante": "nota-credito-000123.pdf",
  "idPedido": "<ID_PEDIDO>"
}
```

### Reglas

- `monto` **mayor que 0**.
- `referenciaPago` obligatoria.
- `comprobante` entre **3 y 255** caracteres.

### Pruebas negativas

| Qué se manda | Esperado |
|---|---|
| `"monto": -50000.0` | **400** |
| `"referenciaPago": ""` | **400** |

---

## 11. Trueques — `/api/trueques`

Necesita **dos prendas distintas** y un usuario proponente.

### POST

```json
{
  "estado": "PENDIENTE",
  "fechaPropuesta": "2026-08-20",
  "fechaRespuesta": null,
  "mensaje": "Te cambio mi buzo gris por tu chaqueta de jean",
  "valorEstimado": 50000.0,
  "aceptado": false,
  "idPrendaOfrecida": "<ID_PRENDA>",
  "idPrendaDeseada": "<ID_PRENDA_2>",
  "idProponente": "<ID_USUARIO>"
}
```

### PUT — aceptar el trueque

```json
{
  "estado": "ACEPTADO",
  "fechaPropuesta": "2026-08-20",
  "fechaRespuesta": "2026-08-21",
  "mensaje": "Acepto el cambio, coordinamos la entrega",
  "valorEstimado": 50000.0,
  "aceptado": true,
  "idPrendaOfrecida": "<ID_PRENDA>",
  "idPrendaDeseada": "<ID_PRENDA_2>",
  "idProponente": "<ID_USUARIO>"
}
```

### Reglas

- `estado` solo puede ser: **PENDIENTE · ACEPTADO · RECHAZADO · CANCELADO**.
- `fechaPropuesta` obligatoria; `fechaRespuesta` no puede ser anterior a ella.
- La prenda ofrecida y la deseada **no pueden ser la misma**.
- `valorEstimado` no puede ser negativo.
- `idProponente` obligatorio.

> Nota: el dato sembrado usa el estado `PROPUESTO`, que **no** está en la lista permitida (la carga inicial entra por `EntityManager` y se salta las validaciones). Por la API hay que usar `PENDIENTE`.

### Pruebas negativas

| Qué se manda | Esperado |
|---|---|
| `"estado": "PROPUESTO"` | **400** estado no válido |
| Misma prenda en ofrecida y deseada | **400** |
| `"fechaRespuesta"` anterior a `fechaPropuesta` | **400** |

---

## 12. Envíos — `/api/envios`

⚠️ **Este endpoint necesita `idTransportista` y `idPuntoAcopio`, y esas dos tablas todavía no tienen controlador** (ver sección 18). Los UUID hay que sacarlos de la consola H2:

```sql
SELECT id, nombre FROM transportistas;
SELECT id, nombre FROM puntos_acopio;
```

### POST

```json
{
  "codigoGuia": "GUIA-000002",
  "estado": "EN_TRANSITO",
  "costo": 12000.0,
  "fechaDespacho": "2026-08-19",
  "fechaEntregaEstimada": "2026-08-22",
  "peso": 1.5,
  "idPedido": "<ID_PEDIDO>",
  "idTransportista": "<ID_TRANSPORTISTA>",
  "idPuntoAcopio": "<ID_PUNTO_ACOPIO>"
}
```

> El campo del request se llama **`peso`**, pero la respuesta lo devuelve como **`pesoKg`**. Inconsistencia para señalarle al equipo de logística.

### PUT

```json
{
  "codigoGuia": "GUIA-000002",
  "estado": "ENTREGADO",
  "costo": 12000.0,
  "fechaDespacho": "2026-08-19",
  "fechaEntregaEstimada": "2026-08-22",
  "peso": 1.5,
  "idPedido": "<ID_PEDIDO>",
  "idTransportista": "<ID_TRANSPORTISTA>",
  "idPuntoAcopio": "<ID_PUNTO_ACOPIO>"
}
```

### Reglas

- `codigoGuia` obligatorio.
- `costo` obligatorio y **≥ 1** (si se manda `null` la validación revienta con 500 — bug conocido).
- `fechaDespacho` obligatoria y **no puede ser futura** (mismo bug con `null`).

### Pruebas negativas

| Qué se manda | Esperado |
|---|---|
| `"codigoGuia": ""` | **400** |
| `"costo": 0` | **400** |
| `"fechaDespacho": "2027-01-01"` | **400** fecha futura |
| `"costo": null` | **500** ← bug a corregir por el equipo |

---

## 13. Seguimiento de envío — `/api/seguimiento-envio`

### POST

```json
{
  "estado": "EN_TRANSITO",
  "descripcion": "El paquete salió del centro de distribución",
  "ubicacion": "Medellín - Belén",
  "fechaHora": "2026-08-20T09:30:00",
  "latitud": 6.2308,
  "longitud": -75.6042,
  "idEnvio": "<ID_ENVIO>"
}
```

### PUT

```json
{
  "estado": "ENTREGADO",
  "descripcion": "Entregado al comprador en la dirección registrada",
  "ubicacion": "Medellín - El Poblado",
  "fechaHora": "2026-08-20T15:45:00",
  "latitud": 6.2088,
  "longitud": -75.5673,
  "idEnvio": "<ID_ENVIO>"
}
```

### Reglas

- `idEnvio` **obligatorio** (la relación no admite nulos).
- `descripcion` obligatoria.
- `ubicacion` entre **3 y 255** caracteres.
- `fechaHora` obligatoria y **no futura**. Formato `AAAA-MM-DDTHH:MM:SS`.

### Pruebas negativas

| Qué se manda | Esperado |
|---|---|
| `"fechaHora": "2027-01-01T10:00:00"` | **400** |
| Sin `idEnvio` | **400 / 404** |
| `"ubicacion": "ab"` | **400** |

---

## 14. Campañas — `/api/campanas`

Ruta extra: `GET /api/campanas/activas`.

### POST

```json
{
  "nombreCampana": "Semana del trueque",
  "descripcionCampana": "Descuentos en prendas publicadas para intercambio",
  "fechaInicio": "2026-09-01T00:00:00",
  "fechaFinal": "2026-09-15T23:59:59",
  "descuentoPct": 20.0,
  "activa": true
}
```

### PUT

```json
{
  "nombreCampana": "Semana del trueque — extendida",
  "descripcionCampana": "Se amplía una semana más por alta demanda",
  "fechaInicio": "2026-09-01T00:00:00",
  "fechaFinal": "2026-09-22T23:59:59",
  "descuentoPct": 25.0,
  "activa": true
}
```

### Reglas

- `nombreCampana` y `descripcionCampana` obligatorios (con tope de longitud).
- Ambas fechas obligatorias y `fechaFinal` **posterior** a `fechaInicio`.
- `descuentoPct` obligatorio, entre **0 y 100**.
- `activa` obligatorio (`true` o `false`).

### Pruebas negativas

| Qué se manda | Esperado |
|---|---|
| `"descuentoPct": 150.0` | **400** |
| `fechaFinal` anterior a `fechaInicio` | **400** |
| `"activa": null` | **400** |

---

## 15. Cupones — `/api/cupones`

Ruta extra: `GET /api/cupones/validos`. Necesita `campanaId`.

### POST — cupón de porcentaje

```json
{
  "codigo": "TRUEQUE20",
  "tipo": "PORCENTAJE",
  "valor": "20",
  "usosMaximos": 100,
  "usosActuales": 0,
  "fechaExpiracion": "2026-09-15T23:59:59",
  "campanaId": "<ID_CAMPANA>"
}
```

### POST — cupón de monto fijo

```json
{
  "codigo": "AHORRA10K",
  "tipo": "MONTO_FIJO",
  "valor": "10000",
  "usosMaximos": 50,
  "usosActuales": 0,
  "fechaExpiracion": "2026-09-15T23:59:59",
  "campanaId": "<ID_CAMPANA>"
}
```

### PUT

```json
{
  "codigo": "TRUEQUE20",
  "tipo": "PORCENTAJE",
  "valor": "25",
  "usosMaximos": 150,
  "usosActuales": 3,
  "fechaExpiracion": "2026-09-22T23:59:59",
  "campanaId": "<ID_CAMPANA>"
}
```

### Reglas

- `tipo` solo: **PORCENTAJE · MONTO_FIJO · ENVIO_GRATIS**.
- `valor` es **String** (sí, entre comillas). Si el tipo es `PORCENTAJE`, debe ser numérico y estar entre **0 y 100**.
- `usosMaximos` > 0; `usosActuales` ≥ 0 y **no puede superar** a `usosMaximos`.
- `fechaExpiracion` obligatoria.
- `campanaId` debe apuntar a una campaña existente.

> Los cupones sembrados usan el tipo `FIJO`, que tampoco está permitido por la validación. Por la API es `MONTO_FIJO`.

### Pruebas negativas

| Qué se manda | Esperado |
|---|---|
| `"tipo": "FIJO"` | **400** tipo no válido |
| `"tipo": "PORCENTAJE", "valor": "abc"` | **400** debe ser numérico |
| `"tipo": "PORCENTAJE", "valor": "150"` | **400** fuera de 0–100 |
| `"usosActuales": 200, "usosMaximos": 100` | **400** |
| Sin `campanaId` | **400** debe estar asociado a una campaña |

---

## 16. Recompensas — `/api/recompensas`

### POST

```json
{
  "nombre": "Bono de 30.000",
  "puntosRequeridos": 300,
  "descripcion": "Descuento de 30.000 pesos en tu próxima compra",
  "existencias": 20,
  "tipo": "DESCUENTO",
  "activa": true
}
```

### PUT

```json
{
  "nombre": "Bono de 30.000",
  "puntosRequeridos": 350,
  "descripcion": "Descuento de 30.000 pesos, válido por 60 días",
  "existencias": 10,
  "tipo": "DESCUENTO",
  "activa": true
}
```

### Reglas

- `nombre` obligatorio.
- `descripcion` entre **3 y 255**.
- `tipo` solo: **DESCUENTO · ENVIO_GRATIS · PRODUCTO_FISICO · EXPERIENCIA**.
- El campo del request se llama `existencias` pero se guarda en la columna `stock`.
- `activa` **siempre se guarda como `true`**, sin importar lo que se mande — bug del equipo de mercadeo.

### Pruebas negativas

| Qué se manda | Esperado |
|---|---|
| `"tipo": "ENVIO"` | **400** tipo no válido |
| `"nombre": ""` | **400** |
| `"activa": false` | Se crea igual, pero guarda `true` ← señalar el bug |

---

## 17. Comunidad

### 17.1 Reseñas — `/api/resenas`

> La ruta **no lleva eñe**: es `/api/resenas`. Colecciones viejas con `/api/reseñas` dan **404**.

#### POST

```json
{
  "titulo": "Muy buena compradora",
  "comentario": "Pagó apenas se acordó el precio y fue clara con la dirección de entrega",
  "fecha": "2026-08-20",
  "recomendado": true,
  "editada": false,
  "visible": true,
  "idAutor": "<ID_USUARIO>",
  "idUsuarioResenado": "<ID_USUARIO_2>"
}
```

#### PUT

```json
{
  "titulo": "Muy buena compradora",
  "comentario": "Corrijo: además de puntual, dejó el punto de encuentro muy cerca",
  "fecha": "2026-08-20",
  "recomendado": true,
  "editada": true,
  "visible": true,
  "idAutor": "<ID_USUARIO>",
  "idUsuarioResenado": "<ID_USUARIO_2>"
}
```

#### Reglas

- `idAutor` e `idUsuarioResenado` son **dos usuarios distintos** y ambos deben existir.
- `comentario` obligatorio, entre **3 y 255** caracteres.
- `fecha` **no puede ser futura**.
- Si se omiten: `editada` = `false`, `visible` = `true`.

#### Pruebas negativas

| Qué se manda | Esperado |
|---|---|
| `"comentario": ""` | **400** |
| `"fecha": "2027-01-01"` | **400** |
| `idAutor` inventado | **404** |

---

### 17.2 Calificaciones — `/api/calificaciones`

Cuelgan de una reseña.

#### POST

```json
{
  "puntaje": 5,
  "dimension": "PUNTUALIDAD",
  "comentarioCorto": "Llegó antes de la hora acordada",
  "fecha": "2026-08-20",
  "verificada": true,
  "peso": 1,
  "idResena": "<ID_RESENA>"
}
```

> Antes este campo se llamaba `reseñaID`, con eñe. Se normalizó a `idResena` para que siga el mismo patrón que `idPedido`, `idAutor` y los demás.

#### PUT

```json
{
  "puntaje": 4,
  "dimension": "COMUNICACION",
  "comentarioCorto": "Respondió rápido, aunque solo en las tardes",
  "fecha": "2026-08-20",
  "verificada": true,
  "peso": 1,
  "idResena": "<ID_RESENA>"
}
```

#### Reglas

- `puntaje` entre **1 y 5**.
- `comentarioCorto` obligatorio.
- `fecha` obligatoria y **no futura**.

#### Pruebas negativas

| Qué se manda | Esperado |
|---|---|
| `"puntaje": 0` o `"puntaje": 6` | **400** |
| `"fecha": "2027-01-01"` | **400** |
| `"comentarioCorto": ""` | **400** |

---

### 17.3 Reportes — `/api/reportes`

#### POST

```json
{
  "motivo": "DESCRIPCION_ENGANOSA",
  "descripcion": "La talla publicada no coincide con la prenda recibida",
  "estado": "ABIERTO",
  "prioridad": "MEDIA",
  "fecha": "2026-08-20",
  "resuelto": false,
  "idReportante": "<ID_USUARIO>",
  "idPrenda": "<ID_PRENDA>"
}
```

#### PUT — cerrar el reporte

```json
{
  "motivo": "DESCRIPCION_ENGANOSA",
  "descripcion": "El vendedor corrigió la publicación y se acordó un reembolso parcial",
  "estado": "CERRADO",
  "prioridad": "BAJA",
  "fecha": "2026-08-20",
  "resuelto": true,
  "idReportante": "<ID_USUARIO>",
  "idPrenda": "<ID_PRENDA>"
}
```

#### Reglas

- `motivo` obligatorio.
- `descripcion` entre **3 y 255**.
- `fecha` **no futura**.
- `estado` y `prioridad` **no se validan** contra una lista — mejora pendiente.

---

## 18. Módulos que todavía NO tienen endpoints

Estas tres tablas existen en la base de datos (y la carga inicial las llena), pero **no tienen controlador**, así que cualquier petición da **404**. Es entrega pendiente de los estudiantes:

| Tabla | Falta | Responsable |
|---|---|---|
| `detalle_pedido` | Repositorio, validación, DTO, servicio y controlador | Toro Palacio |
| `transportistas` | Repositorio, validación, DTO, servicio y controlador | Trujillo Roldán |
| `puntos_acopio` | DTO, servicio y controlador (repositorio y validación ya están) | Méndez Hawasly |

Mientras tanto, para probar **Envíos** hay que sacar los UUID desde `/h2-console`:

```sql
SELECT id, nombre, placa FROM transportistas;
SELECT id, nombre, ciudad FROM puntos_acopio;
SELECT id, cantidad, subtotal FROM detalle_pedido;
```

---

## 19. Resumen de verificaciones por endpoint

Para cada uno de los 16 recursos, la prueba completa es:

1. **POST** con datos válidos → **201** (o **200**) y la respuesta trae `id`.
2. **GET** listado → **200** y aparece el registro recién creado.
3. **GET** por id → **200** con los mismos datos.
4. **PUT** por id → **200** con los datos cambiados.
5. **GET** por id otra vez → confirma que el cambio quedó guardado.
6. **DELETE** por id → **204 No Content**.
7. **GET** por id después de borrar → **404**.
8. **POST** con datos inválidos → **400** con el mensaje de la validación.
9. **GET** con un UUID inventado → **404**.

Si alguno de estos nueve pasos falla, ahí está la capa que le faltó al equipo responsable.
