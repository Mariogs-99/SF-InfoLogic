package com.infologic.cliente_service.services;

import com.infologic.cliente_service.clients.CatalogoFeignClient;
import com.infologic.cliente_service.entities.Cliente;
import com.infologic.cliente_service.exceptions.TransaccionException;
import com.infologic.cliente_service.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final CatalogoFeignClient catalogoFeignClient;

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente findClienteById(Long idCliente) {
        return clienteRepository.findById(idCliente).orElse(null);
    }

    public Cliente guardarCliente(Cliente clienteCNR) {
        String idMhActividadEconomica = null;
        if (clienteCNR.getActividadEconomica() != null && !clienteCNR.getActividadEconomica().isEmpty()) {
            idMhActividadEconomica = catalogoFeignClient.obtenerIDMHPorIdCatalogoYValor("CODIGO_DE_ACTIVIDAD_ECONOMICA", clienteCNR.getActividadEconomica()).orElse(null);
        }

        Optional<String> idMhPais = Optional.empty();
        if (StringUtils.hasText(clienteCNR.getPais())) {
            idMhPais = catalogoFeignClient.obtenerIDMHPorIdCatalogoYValor("PAIS", clienteCNR.getPais());
            if (idMhPais.isEmpty()) {
                throw new TransaccionException("El valor de clienteCNR.pais : '%s' no se encontró en el catálogo %s".formatted(clienteCNR.getPais(), "PAIS"));
            }
        }

        var nuevoCliente = Cliente.builder()
                .nombre(clienteCNR.getNombre())
                .nombreComercial(clienteCNR.getNombreComercial())
                .tipoDocumento(clienteCNR.getTipoDocumento())
                .numeroDocumento(clienteCNR.getNumeroDocumento())
                .pais(idMhPais.orElse("9300"))
                .departamento(clienteCNR.getDepartamento() != null && !clienteCNR.getDepartamento().isEmpty() ? clienteCNR.getDepartamento() : "14")
                .municipio(clienteCNR.getMunicipio() != null && !clienteCNR.getMunicipio().isEmpty() ? clienteCNR.getMunicipio() : "06")
                .direccion(clienteCNR.getDireccion() != null && !clienteCNR.getDireccion().isEmpty() ? clienteCNR.getDireccion().toUpperCase() : "")
                .colonia(clienteCNR.getColonia() != null && !clienteCNR.getColonia().isEmpty() ? clienteCNR.getColonia().toUpperCase() : "")
                .calle(clienteCNR.getCalle() != null && !clienteCNR.getCalle().isEmpty() ? clienteCNR.getCalle().toUpperCase() : "")
                .apartamentoLocal(clienteCNR.getApartamentoLocal() != null && !clienteCNR.getApartamentoLocal().isEmpty() ? clienteCNR.getApartamentoLocal().toUpperCase() : "")
                .numeroCasa(clienteCNR.getNumeroCasa() != null && !clienteCNR.getNumeroCasa().isEmpty() ? clienteCNR.getNumeroCasa().toUpperCase() : "")
                .telefono(clienteCNR.getTelefono())
                .actividadEconomica(idMhActividadEconomica)
                .email(clienteCNR.getEmail())
                .nit(clienteCNR.getNit())
                .nrc(clienteCNR.getNrc())
                .porcentajeDescuento(clienteCNR.getPorcentajeDescuento())
                .descripcionDescuento(clienteCNR.getDescripcionDescuento())
                .esConsumidorFinal(clienteCNR.getEsConsumidorFinal())
                .esExtranjero(clienteCNR.getEsExtranjero())
                .esGobierno(clienteCNR.getEsGobierno())
                .esGranContribuyente(clienteCNR.getEsGranContribuyente())
                .build();
        log.info("Se creará un nuevo cliente: {}", nuevoCliente);
        return clienteRepository.save(nuevoCliente);
    }
}

