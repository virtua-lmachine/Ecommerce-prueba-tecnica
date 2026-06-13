package sales.ecommerce.dto;

import java.time.LocalDateTime;

/**
 * Respuesta con la informacion publica de un usuario.
 */
public record UserResponse(
        /**
         * Identificador unico del usuario.
         */
        Long id,
        /**
         * Nombre de usuario.
         */
        String username,
        /**
         * Correo electronico del usuario.
         */
        String email,
        /**
         * Fecha y hora de registro del usuario.
         */
        LocalDateTime fechaRegistro
) {
}
