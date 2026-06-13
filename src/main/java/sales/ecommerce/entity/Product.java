package sales.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Entidad que representa un producto en el catalogo de comercio electronico
 * Almacena la informacion basica del producto como nombre, precio y stock disponible.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    /**
     * Identificador unico del producto en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del producto.
     */
    @Column(nullable = false, length = 120)
    private String nombre;

    /**
     * Precio del producto con precision de 12 digitos y 2 decimales.
     */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    /**
     * Cantidad disponible en stock del producto.
     */
    @Column(nullable = false)
    private int cantidadStock;
}
