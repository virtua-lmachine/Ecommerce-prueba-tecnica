package sales.ecommerce.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sales.ecommerce.entity.Product;
import sales.ecommerce.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Inicializador de datos de ejemplo para la aplicacion.
 * <p>
 * Se ejecuta al iniciar la aplicacion y carga productos de prueba
 * si la base de datos esta vacia.
 * </p>
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    /**
     * Crea el inicializador de datos.
     *
     * @param productRepository repositorio para guardar productos de prueba
     */
    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Ejecuta la inicializacion de datos al iniciar la aplicacion.
     * <p>
     * Carga productos de ejemplo solo si la base de datos esta vacia.
     * </p>
     *
     * @param args argumentos de linea de comandos (no utilizados)
     */
    @Override
    public void run(String... args) {
        if (productRepository.count() > 0) {
            return;
        }

        productRepository.saveAll(List.of(
                Product.builder()
                        .nombre("Laptop Lenovo IdeaPad")
                        .precio(new BigDecimal("2450000.00"))
                        .cantidadStock(8)
                        .build(),
                Product.builder()
                        .nombre("Mouse inalambrico")
                        .precio(new BigDecimal("65000.00"))
                        .cantidadStock(25)
                        .build(),
                Product.builder()
                        .nombre("Teclado mecanico")
                        .precio(new BigDecimal("180000.00"))
                        .cantidadStock(12)
                        .build(),
                Product.builder()
                        .nombre("Monitor 24 pulgadas")
                        .precio(new BigDecimal("720000.00"))
                        .cantidadStock(0)
                        .build(),
                Product.builder()
                        .nombre("Audifonos Bluetooth")
                        .precio(new BigDecimal("140000.00"))
                        .cantidadStock(16)
                        .build()
        ));
    }
}
