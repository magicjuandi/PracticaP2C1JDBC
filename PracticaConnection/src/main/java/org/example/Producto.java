package org.example;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Producto {
    private Long id;
    private String nombre;
    private Double precio;
    private LocalDate fechaRegistro;

}
