package sales.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Solicitud para agregar un producto a la lista de deseos.
 */
public record WishlistAddRequest(
        /**
         * Identificador del producto a agregar.
         */
        @NotNull Long productId,
        /**
         * Cantidad deseada del producto (minimo 1).
         */
        @Min(1) int cantidadDeseada
) {
}
