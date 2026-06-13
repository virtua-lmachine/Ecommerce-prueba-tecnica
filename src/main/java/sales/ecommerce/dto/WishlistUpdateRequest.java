package sales.ecommerce.dto;

import jakarta.validation.constraints.Min;

/**
 * Solicitud para actualizar la cantidad deseada de un producto en la lista de deseos.
 */
public record WishlistUpdateRequest(
        /**
         * Nueva cantidad deseada del producto (minimo 1).
         */
        @Min(1) int cantidadDeseada
) {
}
