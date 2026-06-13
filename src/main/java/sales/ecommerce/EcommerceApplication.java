package sales.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada principal de la aplicacion de comercio electronico.
 */
@SpringBootApplication
public class EcommerceApplication {

    /**
     * Inicia el contexto de Spring Boot.
     *
     * @param args argumentos recibidos desde la linea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
