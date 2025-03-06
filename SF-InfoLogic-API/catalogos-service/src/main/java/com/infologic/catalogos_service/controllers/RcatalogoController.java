package com.infologic.catalogos_service.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infologic.catalogos_service.services.RcatalogoService;
import com.infologic.catalogos_service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/rcatalogos")
public class RcatalogoController {

    private final RcatalogoService rcatalogoService;
    private Utils utils;

    @Autowired
    public RcatalogoController(RcatalogoService rcatalogoService, Utils utils) {
        this.rcatalogoService = rcatalogoService;
        this.utils = utils;
    }


    @GetMapping(path = "/list")
    public Object rcatalogoList() throws JsonProcessingException {
        return Utils.jsonResponse(200, "listado de Catálogos", rcatalogoService.getAllRcatalogos());
    }

    @GetMapping(path = "/municipios")
    public Object rcatalogoMunicipiosList() throws JsonProcessingException {
        return Utils.jsonResponse(200, "listado de Municipios", rcatalogoService.getRCatalogosByGrupo("MUNICIPIOS"));
    }

    @GetMapping(path = "/giro")
    public Object rcatalogoGiroList() throws JsonProcessingException {
        return Utils.jsonResponse(200, "listado de Actividad Económica", rcatalogoService.getRCatalogosByGrupo("ACTIVIDAD_ECONOMICA"));
    }

    @GetMapping("/{id}")
    public Object getValue(@PathVariable Long id) {

        String rCatalogos = rcatalogoService.getRcatalogosValue(id);
        if (rCatalogos == null) {
            return ResponseEntity.notFound().build();
        }
        return Utils.jsonResponse(200, "Valor del Catálogo", rCatalogos);
    }




}