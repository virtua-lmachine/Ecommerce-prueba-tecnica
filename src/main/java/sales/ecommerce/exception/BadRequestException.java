package sales.ecommerce.exception;

/**
 * Excepcion lanzada cuando una solicitud contiene datos invalidos o incorrectos.
 * Se mapea a un codigo de estado HTTP 400 Bad Request.
 */
public class BadRequestException extends RuntimeException {

    /**
     * Crea una nueva excepcion de solicitud incorrecta.
     */
    public BadRequestException(String message) {
        super(message);
    }
}
