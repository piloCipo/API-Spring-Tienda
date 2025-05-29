package edu.tienda.core.services;

import edu.tienda.core.domain.Producto;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// Este servicio obtiene productos desde una API externa simulada.
// Para usarlo, cambiar `productos.estrategia=EN_BD` por `EN_OTRA_API` en application.properties.
//
// This service fetches products from a simulated external API.
// To use it, change `productos.estrategia=EN_BD` to `EN_OTRA_API` in application.properties.

@Service
@ConditionalOnProperty(
        value = "productos.estrategia",
        havingValue = "EN_OTRA_API")
public class ProductosServiceImplApiExterna implements ProductoService{
    @Override
    public List<Producto> getProductos(){

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Producto>> response = restTemplate.
                exchange("http://localhost:8080/tienda/api/v1/productos/fake-productos",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Producto>>() {
        });
        List<Producto> productos = response.getBody();
        return productos;
    }
    @Override
    public void saveProducto(Producto producto) {
    }

    @Override
    public Producto modificarProducto(Producto producto) {

        return producto;
    }
}
