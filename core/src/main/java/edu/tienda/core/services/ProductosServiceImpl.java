package edu.tienda.core.services;

import edu.tienda.core.domain.Producto;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// Servicio que maneja productos almacenados en memoria (lista local).
// Útil para desarrollo y pruebas sin conexión a base de datos.
// Para usarlo, cambiar `productos.estrategia=EN_BD` a `EN_MEMORIA` en application.properties.
//
// Service that manages products stored in memory (local list).
// Useful for development and testing without a database connection.
// To use it, change `productos.estrategia=EN_BD` to `EN_MEMORIA` in application.properties.


@Service("MEMORY")
@ConditionalOnProperty(
        value = "productos.estrategia",
        havingValue = "EN_MEMORIA")
public class ProductosServiceImpl implements ProductoService {
    private List<Producto> productos = new ArrayList<>(Arrays.asList(
            new Producto(1,"Smart TV", 9000.0, 3),
            new Producto(2,"Pc Notebook",15000.0,10),
            new Producto(3,"Tablet",8000.0,5)
    ));


    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public void saveProducto(Producto producto) {
    }

    @Override
    public Producto modificarProducto(Producto producto){

        return producto;
    }
}
