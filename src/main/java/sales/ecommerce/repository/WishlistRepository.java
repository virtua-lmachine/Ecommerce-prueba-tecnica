package sales.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.ecommerce.entity.Wishlist;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Wishlist.}
 * Proporciona metodos de acceso a datos para la lista de deseos.
 */
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    /**
     * Busca todos los items en la lista de deseos de un usuario.
     */
    List<Wishlist> findByUsuarioId(Long userId);

    /**
     * Busca un item especifico en la lista de deseos de un usuario.
     */
    Optional<Wishlist> findByUsuarioIdAndProductoId(Long userId, Long productId);

    /**
     * Verifica si existe un item en la lista de deseos para el usuario y producto dados.
     */
    boolean existsByUsuarioIdAndProductoId(Long userId, Long productId);
}
