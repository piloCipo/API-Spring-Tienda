package edu.tienda.core.controllers;

import edu.tienda.core.configurations.ConfigurationParameters;
import edu.tienda.core.domain.Producto;
import edu.tienda.core.services.ProductoService;
import edu.tienda.core.services.ProductosServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoControllerRest {

    @Autowired
    @Lazy
    private ProductoService productosService;

    @Autowired
    private ConfigurationParameters configurationParameters;

    @GetMapping
    public ResponseEntity<?> getProductos(){
        System.out.println("params: " + configurationParameters.toString());
        List<Producto> productos = productosService.getProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/fake-productos")
    public ResponseEntity<?> fakeProductos(){
        List<Producto> productos =new ArrayList<>(Arrays
                .asList(
                        new Producto(1,"camiseta juventus", 1200.0,4),
                        new Producto(2,"camisetaa boca",1000.0,8),
                        new Producto(3,"camiseta riber",900.0,1)
                ));
        return ResponseEntity.ok(productos);
    }
}
