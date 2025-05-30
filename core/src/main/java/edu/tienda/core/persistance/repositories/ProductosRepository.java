package edu.tienda.core.persistance.repositories;

import edu.tienda.core.persistance.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<ProductoEntity,Integer> {

    boolean existsByNombre(String nombre);
}
