package com.infologic.cliente_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "catalogos-service", url = "http://catalogos-service:8082/facElec/rcatalogos")
public interface CatalogoFeignClient {

    @GetMapping("/obtener-id-mh")
    Optional<String> obtenerIDMHPorIdCatalogoYValor(@RequestParam String idCatalogo, @RequestParam String valor);
}
