package edu.tienda.core.services;

import edu.tienda.core.domain.Producto;
import edu.tienda.core.persistance.entities.ProductoEntity;
import edu.tienda.core.persistance.repositories.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BD")
@ConditionalOnProperty(
        value = "productos.estrategia",
        havingValue = "EN_BD")
public class ProductosServiceBDImpl implements ProductoService{

    @Autowired
    private ProductosRepository productosRepository;
    public List<Producto> getProductos(){
        List<ProductoEntity> productoEntities = productosRepository.findAll();
        return null;
    }
}
