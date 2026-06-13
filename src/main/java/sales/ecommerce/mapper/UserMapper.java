package sales.ecommerce.mapper;

import sales.ecommerce.dto.UserResponse;
import sales.ecommerce.entity.User;

/**
 * Clase utilitaria para convertir entidades User a DTOs UserResponse.
 */
public class UserMapper {

    /**
     * Constructor privado para evitar instanciacion.
     */
    private UserMapper() {
    }

    /**
     * Convierte una entidad User a un UserResponse.
     */
    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFechaRegistro()
        );
    }
}
