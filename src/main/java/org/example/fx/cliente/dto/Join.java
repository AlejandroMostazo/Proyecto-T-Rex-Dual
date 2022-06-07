package org.example.fx.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Join  {

    int puesto;
    String nombre;
    int puntuacion;
    LocalDateTime fecha;

}