package sales.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.ecommerce.entity.Product;

/**
 * Repositorio JPA para la entidad Product.
 * Proporciona metodos de acceso a datos para productos
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
