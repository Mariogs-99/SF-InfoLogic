package com.infologic.company_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalogo-service", url = "http://localhost:8082/facElec") // Cambia la URL si es necesario
public interface CatalogoClient {

    @GetMapping("/rcatalogos/municipios")
    Object getMunicipios();

    // ✅ Usamos "/rcatalogos/grupo/{grupo}" para evitar conflicto con ID
    @GetMapping("/rcatalogos/grupo/{grupo}")
    Object getRCatalogosAsList(@PathVariable String grupo);

    // ✅ Usamos "/rcatalogos/id/{id}" para evitar conflicto con grupo
    @GetMapping("/rcatalogos/id/{id}")
    String getRcatalogosValue(@PathVariable Long id);



}
