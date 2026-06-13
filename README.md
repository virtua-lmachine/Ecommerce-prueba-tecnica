# API REST E-commerce - Lista de deseos

Proyecto basico en Java 17 con Spring Boot 3, MySQL, JPA y autenticacion JWT manual sin Spring Security.


La API queda disponible en:

-text
http://localhost:8080



Usa el token devuelto en los demas endpoints:

http
Authorization: Bearer TU_TOKEN

## Para crear un usuario, debes hacer una petición POST a /api/auth/register con el siguiente JSON:
- {
  "username": "juanperez",
  "email": "juan.perez@ejemplo.com",
  "password": "miContraseña123"
  }


## Endpoints

- `POST /api/auth/register`: registra usuario y devuelve JWT.
- `POST /api/auth/login`: autentica usuario y devuelve JWT.
- `GET /api/products`: lista productos.
- `GET /api/products/{id}`: obtiene un producto.
- `GET /api/wishlist`: lista la wishlist del usuario autenticado.
- `POST /api/wishlist`: agrega producto a wishlist.
- `PUT /api/wishlist/{productId}`: actualiza cantidad deseada.
- `DELETE /api/wishlist/{productId}`: elimina producto de wishlist.

Ejemplo para agregar producto:

json
{
  "productId": 1,
  "cantidadDeseada": 2
}


Ejemplo para actualizar cantidad:

json
{
  "cantidadDeseada": 3
}

