package edu.tienda.core.controllers;

import edu.tienda.core.configurations.ConfigurationParameters;
import edu.tienda.core.domain.Cliente;
import edu.tienda.core.domain.Producto;
import edu.tienda.core.exceptions.ResourceNotFoundException;
import edu.tienda.core.services.ProductoService;
import edu.tienda.core.services.ProductosServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

        // Imprime en consola los valores actuales de configuración definidos en application.properties,
        // útiles para verificar que se estén cargando correctamente en tiempo de ejecución.
        // Prints to the console the current configuration values defined in application.properties,
        // useful for verifying they are loaded correctly at runtime.
        System.out.println("params: " + configurationParameters.toString());
        List<Producto> productos = productosService.getProductos();
        return ResponseEntity.ok(productos);
    }


    @PostMapping
    public ResponseEntity<?> altaProducto(@RequestBody Producto producto){
        productosService.saveProducto(producto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id")
                .buildAndExpand(producto.getId())
                .toUri();
        return ResponseEntity.created(location).body(producto);
    }

    @PutMapping
    public ResponseEntity<?> modificacionProducto(@RequestBody Producto producto){
        Producto productoActualizado = productosService.modificarProducto(producto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable int id) {
        productosService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }




    // Endpoint de prueba para obtener productos fake.
    // Útil para testing o desarrollo rápido sin conexión a base de datos o servicios externos.
    //
    // Test endpoint that returns mock products.
    // Useful for testing or quick development without DB or external APIs.

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
