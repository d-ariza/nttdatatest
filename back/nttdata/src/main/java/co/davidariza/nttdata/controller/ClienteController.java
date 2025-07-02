package co.davidariza.nttdata.controller;

import co.davidariza.nttdata.dto.ClienteDTO;
import co.davidariza.nttdata.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST que expone el endpoint para consultar información del cliente.
 * Utiliza ResponseEntity para controlar los códigos HTTP.
 */
@Slf4j
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    // Inyección de dependencias por constructor
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Endpoint que retorna la información básica del cliente si los parámetros son válidos.
     *
     * @param tipo Tipo de documento (C o P)
     * @param numero Número de documento
     * @return ClienteDTO con la información mockeada
     */
    @GetMapping
    public ResponseEntity<ClienteDTO> obtenerCliente(
            @RequestParam String tipo,
            @RequestParam String numero
    ) {
        log.info("Recibida petición GET /clientes con tipo={} y número={}", tipo, numero);
        ClienteDTO cliente = clienteService.obtenerCliente(tipo, numero);
        log.debug("Respuesta exitosa para cliente: {}", cliente);
        return ResponseEntity.ok(cliente); // 200 OK automático con el body
    }
}
