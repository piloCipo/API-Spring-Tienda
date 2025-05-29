package edu.tienda.core.services;

import edu.tienda.core.domain.Producto;
import edu.tienda.core.exceptions.ResourceNotFoundException;
import edu.tienda.core.persistance.entities.ProductoEntity;
import edu.tienda.core.persistance.repositories.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("BD")
@ConditionalOnProperty(
        value = "productos.estrategia",
        havingValue = "EN_BD")
public class ProductosServiceBDImpl implements ProductoService{

    @Autowired
    private ProductosRepository productosRepository;

    public List<Producto> getProductos(){
        List<Producto> productos = productosRepository.findAll().
                stream().map(productoEntity -> {
                    Producto producto = new Producto();
                    producto.setId(productoEntity.getId());
                    producto.setNombre(productoEntity.getNombre());
                    producto.setPrecio(productoEntity.getPrecio());
                    producto.setStock(productoEntity.getStock());
                    return producto;
                }).collect(Collectors.toList());
        return productos;
    }

    @Override
    public void saveProducto(Producto producto){
        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setNombre(producto.getNombre());
        productoEntity.setPrecio(producto.getPrecio());
        productoEntity.setStock(producto.getStock());

        productosRepository.save(productoEntity);
    }

    @Override
    public Producto modificarProducto(Producto producto) {
        ProductoEntity entity = productosRepository.findById(producto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto con id " + producto.getId() + " no encontrado"));

        entity.setNombre(producto.getNombre());
        entity.setPrecio(producto.getPrecio());
        entity.setStock(producto.getStock());

        productosRepository.save(entity);

        // Convertir de Entity a modelo de dominio
        Producto actualizado = new Producto();
        actualizado.setId(entity.getId());
        actualizado.setNombre(entity.getNombre());
        actualizado.setPrecio(entity.getPrecio());
        actualizado.setStock(entity.getStock());

        return actualizado;
    }

    @Override
    public void deleteProducto(int id) {
        productosRepository.deleteById(id);
    }


}
