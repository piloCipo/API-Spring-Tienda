package edu.tienda.core.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tienda.core.domain.Producto;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.List;

// Este servicio carga productos desde un archivo JSON en resources.
// Para usarlo, cambiar `productos.estrategia=EN_BD` por `EN_JSON` en application.properties.
// This service loads products from a JSON file in the resources folder.
// To use it, change `productos.estrategia=EN_BD` to `EN_JSON` in application.properties.

@Service("JSON")
@ConditionalOnProperty(
        value = "productos.estrategia",
        havingValue = "EN_JSON")
public class ProductosServiceJSONImpl implements ProductoService{
    public List<Producto> getProductos(){
        List<Producto> productos;
        try {
            productos = new ObjectMapper()
                    .readValue(this.getClass().getResourceAsStream("/productos.json"),
                            new TypeReference<List<Producto>>() {});
            return productos;
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveProducto(Producto producto) {
    }

    @Override
    public Producto modificarProducto(Producto producto) {

        return producto;
    }
}
