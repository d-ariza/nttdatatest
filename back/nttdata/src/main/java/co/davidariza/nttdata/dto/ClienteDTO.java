package co.davidariza.nttdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClienteDTO representa la estructura de datos que se retornará al frontend en formatoJSON.
 * Usamos Lombok para evitar escribir getters, setters y constructores pero poder exponer los datos de la prueba.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String direccion;
    private String ciudad;
}
