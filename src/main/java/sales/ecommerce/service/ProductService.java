package sales.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sales.ecommerce.dto.ProductResponse;
import sales.ecommerce.exception.ResourceNotFoundException;
import sales.ecommerce.mapper.ProductMapper;
import sales.ecommerce.repository.ProductRepository;

import java.util.List;

/**
 * Servicio de consulta para el catalogo de productos.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Crea el servicio de productos.
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Lista todos los productos disponibles en la base de datos.
     */
    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    /**
     * Busca un producto por su identificador.
     */
    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }
}
