package sales.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entidad que representa un item en la lista de deseos de un usuario.
 * Relaciona un usuario con un producto que desea comprar, incluyendo la cantidad deseada
 * y la fecha en que fue agregado a la lista.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "wishlist",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "product_id"})
)
public class Wishlist {

    /**
     * Identificador unico del item en la lista de deseos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Usuario que agrego el producto a su lista de deseos.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User usuario;

    /**
     * Producto que el usuario desea comprar.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product producto;

    /**
     * Cantidad deseada del producto por el usuario.
     */
    private int cantidadDeseada;

    /**
     * Fecha y hora en que el producto fue agregado a la lista de deseos.
     */
    private LocalDateTime fechaAgregado;
}
