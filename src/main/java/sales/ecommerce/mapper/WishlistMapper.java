package sales.ecommerce.mapper;

import sales.ecommerce.dto.WishlistResponse;
import sales.ecommerce.entity.Product;
import sales.ecommerce.entity.Wishlist;

/**
 * Clase utilitaria para convertir entidades Wishlist a DTOs WishlistResponse.
 */
public class WishlistMapper {

    /**
     * Constructor privado para evitar instanciacion.
     */
    private WishlistMapper() {
    }

    /**
     * Convierte una entidad Wishlist a un WishlistResponse.
     * Incluye informacion del producto asociado y estado del stock.
     */
    public static WishlistResponse toResponse(Wishlist wishlist) {
        Product product = wishlist.getProducto();
        boolean outOfStock = product.getCantidadStock() <= 0;
        String message = outOfStock ? "Producto sin stock disponible" : "Producto disponible";

        return new WishlistResponse(
                wishlist.getId(),
                product.getId(),
                product.getNombre(),
                product.getPrecio(),
                product.getCantidadStock(),
                wishlist.getCantidadDeseada(),
                outOfStock,
                message,
                wishlist.getFechaAgregado()
        );
    }
}
