package edu.tienda.core.services;

import edu.tienda.core.domain.Producto;

import java.util.List;

public interface ProductoService {
    public List<Producto> getProductos();

    public Producto saveProducto(Producto producto);

    public Producto modificarProducto(Producto producto);

    public default void deleteProducto(int id){

    }
}
