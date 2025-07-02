package co.davidariza.nttdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando no se encuentra un cliente con los datos recibidos.
 * Devuelve un código HTTP 404 (Not Found).
 */

public class ClienteNotFoundException extends RuntimeException{
    public ClienteNotFoundException(String mensaje) {
        super(mensaje);
    }
}
