package sales.ecommerce.dto;

/**
 * Respuesta de autenticacion que contiene el token JWT y datos del usuario.
 * Se devuelve despues de un registro o inicio de sesion exitoso.
 */
public record AuthResponse(
        /**
         * Token JWT generado para autenticar al usuario.
         */
        String token,
        /**
         * Tipo de token (generalmente "Bearer").
         */
        String tokenType,
        /**
         * Informacion publica del usuario autenticado.
         */
        UserResponse user
) {
}
