package sales.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Solicitud de registro de un nuevo usuario.
 */
public record RegisterRequest(
        /**
         * Nombre de usuario unico (entre 3 y 60 caracteres).
         */
        @NotBlank @Size(min = 3, max = 60) String username,
        /**
         * Correo electronico unico del usuario (maximo 120 caracteres).
         */
        @NotBlank @Email @Size(max = 120) String email,
        /**
         * Contrasena del usuario (entre 6 y 80 caracteres).
         */
        @NotBlank @Size(min = 6, max = 80) String password
) {
}
