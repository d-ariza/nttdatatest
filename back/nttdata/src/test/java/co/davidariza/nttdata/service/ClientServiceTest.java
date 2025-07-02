package co.davidariza.nttdata.service;

import co.davidariza.nttdata.dto.ClienteDTO;
import co.davidariza.nttdata.exception.BadRequestException;
import co.davidariza.nttdata.exception.ClienteNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientServiceTest {
    /**
     * Validar casos del método obtenerCliente(...):
     * Cliente válido — retorna DTO mockeado
     * Tipo de documento inválido — lanza BadRequestException
     * Campos vacíos — lanza BadRequestException
     * Cliente no existente — lanza ClienteNotFoundException
     */
    private final ClienteService clienteService = new ClienteService();

    @Test
    @DisplayName("Retorna cliente si tipo='C' y número='23445322'")
    void obtenerCliente_Valido() {
        ClienteDTO cliente = clienteService.obtenerCliente("C", "23445322");

        assertNotNull(cliente);
        assertEquals("Juan", cliente.getPrimerNombre());
        assertEquals("Bogotá", cliente.getCiudad());
    }

    @Test
    @DisplayName("Lanza BadRequest si tipo inválido")
    void obtenerCliente_TipoInvalido_LanzaExcepcion() {
        BadRequestException ex = assertThrows(BadRequestException.class, () ->
                clienteService.obtenerCliente("X", "123"));

        assertEquals("Tipo de documento inválido. Use 'C' o 'P'.", ex.getMessage());
    }

    @Test
    @DisplayName("Lanza BadRequest si campos vacíos")
    void obtenerCliente_Vacios_LanzaExcepcion() {
        BadRequestException ex = assertThrows(BadRequestException.class, () ->
                clienteService.obtenerCliente("", ""));

        assertEquals("El tipo y número de documento son obligatorios", ex.getMessage());
    }

    @Test
    @DisplayName("Lanza ClienteNotFound si tipo/número válidos pero no mockeados")
    void obtenerCliente_NoExiste_LanzaNotFound() {
        ClienteNotFoundException ex = assertThrows(ClienteNotFoundException.class, () ->
                clienteService.obtenerCliente("C", "99999999"));

        assertEquals("Cliente no encontrado con los datos proporcionados", ex.getMessage());
    }

}
