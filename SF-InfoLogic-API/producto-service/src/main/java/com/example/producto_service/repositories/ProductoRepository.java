package com.example.producto_service.repositories;

import com.example.producto_service.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {}
