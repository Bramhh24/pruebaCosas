package com.bramh.pruebaCosas.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonaDTO {
    private String idPersona;
    private String nombres;
    private String apellidos;
    private int edad;
    private String genero;
    private Boolean status;
}
