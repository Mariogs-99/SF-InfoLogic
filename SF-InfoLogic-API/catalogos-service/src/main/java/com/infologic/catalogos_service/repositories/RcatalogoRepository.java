package com.infologic.catalogos_service.repositories;


import com.infologic.catalogos_service.entities.RCatalogos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RcatalogoRepository extends JpaRepository<RCatalogos, Long> {

    List<RCatalogos> findByGrupo(String grupo);

    List<RCatalogos> findByIdCatalogo(Long id);
}
