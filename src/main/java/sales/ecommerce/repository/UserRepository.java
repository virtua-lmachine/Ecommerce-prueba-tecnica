package sales.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.ecommerce.entity.User;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad User.
 * Proporciona metodos de acceso a datos para usuarios.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por su nombre de usuario.
     */
    Optional<User> findByUsername(String username);

    /**
     * Verifica si existe un usuario con el nombre de usuario dado.
     */
    boolean existsByUsername(String username);

    /**
     * Verifica si existe un usuario con el correo electronico dado.
     */
    boolean existsByEmail(String email);
}
