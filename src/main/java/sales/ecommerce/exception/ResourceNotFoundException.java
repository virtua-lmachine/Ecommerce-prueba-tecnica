package sales.ecommerce.exception;

/**
 * Excepcion lanzada cuando no se encuentra un recurso solicitado.
 * Se mapea a un codigo de estado HTTP 404 Not Found.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Crea una nueva excepcion de recurso no encontrado.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
