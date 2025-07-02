package co.davidariza.nttdata.controller;

import co.davidariza.nttdata.dto.ClienteDTO;
import co.davidariza.nttdata.exception.BadRequestException;
import co.davidariza.nttdata.exception.ClienteNotFoundException;
import co.davidariza.nttdata.service.ClienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    @DisplayName("Devuelve 200 cuando el cliente es válido")
    void obtenerCliente_Devuelve200() throws Exception {
        when(clienteService.obtenerCliente("C", "23445322"))
                .thenReturn(MockFactory.clienteValido());

        mockMvc.perform(get("/clientes")
                        .param("tipo", "C")
                        .param("numero", "23445322")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primerNombre").value("Juan"))
                .andExpect(jsonPath("$.ciudad").value("Bogotá"));
    }

    @Test
    @DisplayName("Devuelve 400 cuando el tipo de documento es inválido")
    void obtenerCliente_TipoDocumentoInvalido_Devuelve400() throws Exception {
        when(clienteService.obtenerCliente("X", "12345"))
                .thenThrow(new BadRequestException("Tipo de documento inválido"));

        mockMvc.perform(get("/clientes")
                        .param("tipo", "X")
                        .param("numero", "12345"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("Tipo de documento inválido"));
    }

    @Test
    @DisplayName("Devuelve 404 cuando el cliente no existe")
    void obtenerCliente_NoExiste_Devuelve404() throws Exception {
        when(clienteService.obtenerCliente("C", "99999999"))
                .thenThrow(new ClienteNotFoundException("Cliente no encontrado"));

        mockMvc.perform(get("/clientes")
                        .param("tipo", "C")
                        .param("numero", "99999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje").value("Cliente no encontrado"));
    }

    @Test
    @DisplayName("Devuelve 400 cuando falta algún parámetro")
    void obtenerCliente_ParametrosIncompletos_Devuelve400() throws Exception {
        mockMvc.perform(get("/clientes")
                        .param("tipo", "C")) // Falta 'numero'
                .andExpect(status().isBadRequest());
    }
}
