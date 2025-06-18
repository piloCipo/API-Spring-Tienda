package edu.tienda.core.services;

import edu.tienda.core.domain.Producto;
import edu.tienda.core.exceptions.ConflictException;
import edu.tienda.core.exceptions.ResourceNotFoundException;
import edu.tienda.core.persistance.entities.ProductoEntity;
import edu.tienda.core.persistance.repositories.ProductosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {
    @Mock
    private ProductosRepository productosRepository;
    @InjectMocks
    private ProductosServiceBDImpl productoService;
    private ProductoEntity productoEjemplo;

    @BeforeEach
    void setUp() {
        productoEjemplo = new ProductoEntity();
        productoEjemplo.setId(1);
        productoEjemplo.setNombre("Café");
        productoEjemplo.setPrecio(100.0);
        productoEjemplo.setStock(10);
    }

    @Test
    void debeObtenerTodosLosProductos() {
        List<ProductoEntity> listaMock = List.of(productoEjemplo);

        when(productosRepository.findAll()).thenReturn(listaMock);

        List<Producto> resultado = productoService.getProductos(); // este método los convierte

        assertEquals(1, resultado.size());
        assertEquals("Café", resultado.get(0).getNombre());
    }

    @Test
    void debeGuardarProductoCorrectamente() {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre("Té");
        nuevoProducto.setPrecio(50.0);
        nuevoProducto.setStock(20);

        when(productosRepository.existsByNombre("Té")).thenReturn(false);

        ProductoEntity entityGuardada = new ProductoEntity();
        entityGuardada.setId(2);
        entityGuardada.setNombre("Té");
        entityGuardada.setPrecio(50.0);
        entityGuardada.setStock(20);

        when(productosRepository.save(any())).thenReturn(entityGuardada);

        Producto resultado = productoService.saveProducto(nuevoProducto);

        assertEquals("Té", resultado.getNombre());
        assertEquals(2, resultado.getId());
    }

    @Test
    void debeLanzarExcepcionSiProductoYaExiste() {
        // Arrange
        Producto producto = new Producto();
        producto.setNombre("Café");

        when(productosRepository.existsByNombre("Café")).thenReturn(true);

        // Act & Assert
        assertThrows(ConflictException.class, () -> {
            productoService.saveProducto(producto);
        });
    }

    @Test
    void debeModificarProductoExistente() {
        Producto productoModificado = new Producto();
        productoModificado.setId(1);
        productoModificado.setNombre("mate");
        productoModificado.setPrecio(123.43);
        productoModificado.setStock(24);

        ProductoEntity productoEntityExistente = new ProductoEntity();
        productoEntityExistente.setId(1);
        productoEntityExistente.setNombre("yerba mate");
        productoEntityExistente.setPrecio(100.0);
        productoEntityExistente.setStock(12);

        when(productosRepository.findById(1)).thenReturn(Optional.of(productoEntityExistente));
        when(productosRepository.save(any(ProductoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Producto resultado = productoService.modificarProducto(productoModificado);

        assertEquals(1, resultado.getId());
        assertEquals("mate", resultado.getNombre());
        assertEquals(123.43, resultado.getPrecio());
        assertEquals(24, resultado.getStock());

    }

    @Test
    void debeLanzarExcepcionCuandoProductoNoExisteAlModificar() {
        Producto productoInexistente = new Producto();
        productoInexistente.setId(999);
        productoInexistente.setNombre("no existe");
        productoInexistente.setPrecio(23.0);
        productoInexistente.setStock(3);

        when(productosRepository.findById(999)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            productoService.modificarProducto(productoInexistente);
        });

        assertEquals("Producto con id 999 no encontrado", exception.getMessage());
    }

    @Test
    void debeBorrarProductoExistente () {
        int id = 1;
        when(productosRepository.findById(id)).thenReturn(Optional.of(productoEjemplo));
        productoService.deleteProducto(id);
        verify(productosRepository).delete(productoEjemplo);
    }

    @Test
    void debeLanzarExcepcionCuandoProductoNoExisteAlBorrar() {
        int id = 999;
        when(productosRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            productoService.deleteProducto(id);
        });

        assertEquals("Producto con id " + id + " no encontrado", ex.getMessage());
    }
}
