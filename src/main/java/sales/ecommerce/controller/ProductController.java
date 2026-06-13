package sales.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sales.ecommerce.dto.ProductResponse;
import sales.ecommerce.service.ProductService;

import java.util.List;

/**
 * Controlador REST para consultar productos disponibles en el catalogo.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Crea el controlador de productos.
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Obtiene todos los productos registrados.
     */
    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }

    /**
     * Busca un producto por su identificador.
     */
    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id) {
        return productService.findById(id);
    }
}
