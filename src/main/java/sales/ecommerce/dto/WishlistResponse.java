package sales.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Respuesta con la informacion de un item en la lista de deseos.
 * Incluye detalles del producto, cantidad deseada y estado del stock.
 */
public record WishlistResponse(
        /**
         * Identificador unico del item en la lista de deseos.
         */
        Long id,
        /**
         * Identificador del producto.
         */
        Long productId,
        /**
         * Nombre del producto.
         */
        String nombreProducto,
        /**
         * Precio del producto.
         */
        BigDecimal precio,
        /**
         * Cantidad disponible en stock.
         */
        int cantidadStock,
        /**
         * Cantidad deseada por el usuario.
         */
        int cantidadDeseada,
        /**
         * Indica si el producto esta agotado.
         */
        boolean outOfStock,
        /**
         * Mensaje descriptivo sobre el estado del stock.
         */
        String mensajeStock,
        /**
         * Fecha y hora en que se agrego el producto a la lista.
         */
        LocalDateTime fechaAgregado
) {
}
