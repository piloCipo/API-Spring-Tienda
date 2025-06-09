package edu.tienda.core.services;

import edu.tienda.core.domain.Producto;
import edu.tienda.core.persistance.entities.ProductoEntity;
import edu.tienda.core.persistance.repositories.ProductosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {
    @Mock
    private ProductosRepository productosRepository;
    @InjectMocks
    private ProductoService productoService;
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

}
