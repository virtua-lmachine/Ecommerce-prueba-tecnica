package sales.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Respuesta de error estandarizada para todas las excepciones de la API
 * Contiene informacion detallada sobre el error ocurrido, incluyendo timestamp,
 * codigo HTTP, tipo de error, mensaje y campos invalidos si aplica.
 */
public record ErrorResponse(
        /**
         * Fecha y hora en que ocurrio el error.
         */
        LocalDateTime timestamp,
        /**
         * Codigo de estado HTTP del error.
         */
        int status,
        /**
         * Tipo de error segun el estandar HTTP.
         */
        String error,
        /**
         * Mensaje descriptivo del error.
         */
        String message,
        /**
         * Mapa de campos con errores de validacion y sus mensajes.
         */
        Map<String, String> fields
) {
}
