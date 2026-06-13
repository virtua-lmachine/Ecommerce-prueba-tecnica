package sales.ecommerce.dto;

import java.math.BigDecimal;

/**
 * Respuesta con la informacion publica de un producto.
 */
public record ProductResponse(
        /**
         * Identificador unico del producto.
         */
        Long id,
        /**
         * Nombre del producto.
         */
        String nombre,
        /**
         * Precio del producto.
         */
        BigDecimal precio,
        /**
         * Cantidad disponible en stock.
         */
        int cantidadStock
) {
}
