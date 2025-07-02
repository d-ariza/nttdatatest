package co.davidariza.nttdata.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalExceptionHandlerTest {
    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    @DisplayName("Maneja BadRequestException con código 400")
    void manejarBadRequest_Retorna400() {
        BadRequestException ex = new BadRequestException("Parámetros inválidos");

        ResponseEntity<ErrorResponse> respuesta = handler.manejarBadRequest(ex);

        assertEquals(400, respuesta.getStatusCodeValue());
        assertEquals("Parámetros inválidos", respuesta.getBody().getMensaje());
        assertNotNull(respuesta.getBody().getTimestamp());
    }

    @Test
    @DisplayName("Maneja ClienteNotFoundException con código 404")
    void manejarClienteNotFound_Retorna404() {
        ClienteNotFoundException ex = new ClienteNotFoundException("Cliente no encontrado");

        ResponseEntity<ErrorResponse> respuesta = handler.manejarClienteNoEncontrado(ex);

        assertEquals(404, respuesta.getStatusCodeValue());
        assertEquals("Cliente no encontrado", respuesta.getBody().getMensaje());
        assertNotNull(respuesta.getBody().getTimestamp());
    }

    @Test
    @DisplayName("Maneja excepción genérica con código 500 y mensaje controlado")
    void manejarExcepcionGenerica_Retorna500() {
        Exception ex = new RuntimeException("Algo salió mal en backend");

        ResponseEntity<ErrorResponse> respuesta = handler.manejarErroresGenericos(ex);

        assertEquals(500, respuesta.getStatusCodeValue());
        assertEquals("Ha ocurrido un error inesperado. Intenta más tarde.",
                respuesta.getBody().getMensaje());
        assertNotNull(respuesta.getBody().getTimestamp());
    }
}
