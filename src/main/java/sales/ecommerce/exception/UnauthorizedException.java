package sales.ecommerce.exception;

/**
 * Excepcion lanzada cuando un usuario no esta autorizado para realizar una accion.
 * Se mapea a un codigo de estado HTTP 401 Unauthorized.
 */
public class UnauthorizedException extends RuntimeException {

    /**
     * Crea una nueva excepcion de no autorizado.
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}
