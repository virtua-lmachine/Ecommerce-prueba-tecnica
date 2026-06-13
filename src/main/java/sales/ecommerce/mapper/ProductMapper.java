package sales.ecommerce.mapper;

import sales.ecommerce.dto.ProductResponse;
import sales.ecommerce.entity.Product;

/**
 * Clase utilitaria para convertir entidades Product a DTOs ProductResponse.
 */
public class ProductMapper {

    /**
     * Constructor privado para evitar instanciacion.
     */
    private ProductMapper() {
    }

    /**
     * Convierte una entidad Product a un ProductResponse.
     */
    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getNombre(),
                product.getPrecio(),
                product.getCantidadStock()
        );
    }
}
