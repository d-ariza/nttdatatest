package co.davidariza.nttdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando los parámetros de entrada son inválidos.
 * Se asocia directamente con el código HTTP 400 (Bad Request).
 */

public class BadRequestException extends RuntimeException {
    public BadRequestException(String mensaje) {
        super(mensaje);
    }
}
