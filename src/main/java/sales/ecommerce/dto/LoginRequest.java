package sales.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Solicitud de inicio de sesion con credenciales del usuario.
 */
public record LoginRequest(
        /**
         * Nombre de usuario del usuario.
         */
        @NotBlank String username,
        /**
         * Contrasena del usuario.
         */
        @NotBlank String password
) {
}
