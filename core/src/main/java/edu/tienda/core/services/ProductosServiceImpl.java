package edu.tienda.core.services;

import edu.tienda.core.domain.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductosServiceImpl implements ProductoService {
    private List<Producto> productos = new ArrayList<>(Arrays.asList(
            new Producto(1,"Smart TV", 9000.0, 3),
            new Producto(2,"Pc Notebook",15000.0,10),
            new Producto(3,"Tablet",8000.0,5)
    ));

    public List<Producto> getProductos() {
        return productos;
    }
}
