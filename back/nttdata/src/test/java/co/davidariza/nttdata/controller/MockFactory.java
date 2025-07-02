package co.davidariza.nttdata.controller;

import co.davidariza.nttdata.dto.ClienteDTO;

/**
 * Clase de utilidad para generar instancias simuladas (mock) de objetos
 * usados en pruebas automatizadas.
 */
public class MockFactory {

        public static ClienteDTO clienteValido() {
            return new ClienteDTO(
                    "Juan",
                    "Carlos",
                    "Ramírez",
                    "Lopez",
                    "3124567890",
                    "Cra 10 # 20-30",
                    "Bogotá"
            );
        }
}
