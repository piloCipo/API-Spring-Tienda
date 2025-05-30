package edu.tienda.core.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private Integer id;

    @NotBlank(message = "nombre obligatorio")
    private String nombre;
    @NotNull (message = "precio obligatorio")
    @PositiveOrZero (message = "no puede ser negativo")
    private Double precio;
    @NotNull (message = "stock obligatorio")
    @Min(value = 0, message = "stock no puede ser negativo")
    private Integer stock;


}
