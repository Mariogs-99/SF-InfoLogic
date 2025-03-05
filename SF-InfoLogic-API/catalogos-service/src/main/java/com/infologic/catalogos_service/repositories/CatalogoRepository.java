package com.infologic.catalogos_service.repositories;

import com.infologic.catalogos_service.entities.RCatalogos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogoRepository extends JpaRepository<RCatalogos, Long> {

    Optional<RCatalogos> findByCatPadreAndValorIgnoreCase(String catPadre, String valor);


}
