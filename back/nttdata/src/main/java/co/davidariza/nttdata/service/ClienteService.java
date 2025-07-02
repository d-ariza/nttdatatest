package co.davidariza.nttdata.service;

import co.davidariza.nttdata.dto.ClienteDTO;
import co.davidariza.nttdata.exception.BadRequestException;
import co.davidariza.nttdata.exception.ClienteNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ClienteService contiene la lógica de negocio encargada de validar los parámetros
 * y retornar la información del cliente si coincide con los criterios definidos.
 */
@Slf4j
@Service
public class ClienteService {
    /**
     * Retorna los datos mockeados del cliente si el tipo y número coinciden.
     *
     * @param tipoDocumento   Tipo de documento: sólo válido "C" o "P".
     * @param numeroDocumento Número del documento.
     * @return ClienteDTO con la información mock.
     * @throws BadRequestException        Si los parámetros no son válidos.
     * @throws ClienteNotFoundException  Si no existe el cliente en el mock.
     */

    public ClienteDTO obtenerCliente(String tipoDocumento, String numeroDocumento) {
        // Validación de parámetros vacíos o nulos
        log.info("Validando cliente con tipoDocumento={} y numeroDocumento={}", tipoDocumento, numeroDocumento);
        if (tipoDocumento == null || numeroDocumento == null || tipoDocumento.isBlank() || numeroDocumento.isBlank()) {
            log.warn("Campos vacíos o nulos en la solicitud");
            throw new BadRequestException("El tipo y número de documento son obligatorios");
        }

        // Validación del tipo de documento permitido
        if (!tipoDocumento.equalsIgnoreCase("C") && !tipoDocumento.equalsIgnoreCase("P")) {
            log.warn("Tipo de documento inválido recibido: {}", tipoDocumento);
            throw new BadRequestException("Tipo de documento inválido. Use 'C' o 'P'.");
        }

        // Sólo retornamos si cumple con el mock específico
        if (tipoDocumento.equalsIgnoreCase("C") && numeroDocumento.equals("23445322")) {
            log.info("Cliente encontrado: tipo={}, número={}", tipoDocumento, numeroDocumento);
            return new ClienteDTO(
                    "Maria",
                    "Joaquina",
                    "Lopez",
                    "Perez",
                    "3124567890",
                    "Carrera 2 # 10-11",
                    "Bogotá"
            );
        }
        log.info("No se encontró cliente con tipo={} y número={}", tipoDocumento, numeroDocumento);

        // Si no coincide con el cliente mockeado
        throw new ClienteNotFoundException("Cliente no encontrado con los datos proporcionados");
    }

}
