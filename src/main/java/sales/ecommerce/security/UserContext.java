package sales.ecommerce.security;

import sales.ecommerce.entity.User;

/**
 * Clase utilitaria para gestionar el contexto del usuario autenticado.
 * Usa ThreadLocal para almacenar el usuario actual en el hilo de ejecucion.
 */
public class UserContext {

    private static final ThreadLocal<User> CURRENT_USER = new ThreadLocal<>();

    /**
     * Constructor privado para evitar instanciacion.
     */
    private UserContext() {
    }

    /**
     * Establece el usuario autenticado en el contexto del hilo actual.
     */
    public static void setCurrentUser(User user) {
        CURRENT_USER.set(user);
    }

    /**
     * Obtiene el usuario autenticado del contexto del hilo actual.
     */
    public static User getCurrentUser() {
        return CURRENT_USER.get();
    }

    /**
     * Limpia el contexto del usuario del hilo actual.
     */
    public static void clear() {
        CURRENT_USER.remove();
    }
}
