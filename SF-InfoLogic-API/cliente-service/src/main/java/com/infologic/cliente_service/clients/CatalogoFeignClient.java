package com.infologic.cliente_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "catalogos-service", url = "http://localhost:8082/facElec")
public interface CatalogoFeignClient {

    @GetMapping("/rcatalogos/{idCatalogo}/{valor}")  // Se mantiene "/rcatalogos"
    Optional<String> obtenerIDMHPorIdCatalogoYValor(@PathVariable String idCatalogo, @PathVariable String valor);
}
