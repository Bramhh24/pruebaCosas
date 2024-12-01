package com.bramh.pruebaCosas.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CosaDTO {

    private Long idCosa;
    private String tipo;
    private String nombre;
    private String descripcion;
    private Boolean status;
    private PersonaDTO propietario;

    public static CosaDTO fromEntity(Cosa cosa, PersonaDTO persona) {
        return new CosaDTO(cosa.getIdCosa(), cosa.getNombre(), cosa.getTipo(), cosa.getDescripcion(),
                cosa.getStatus(), persona);
    }
}
