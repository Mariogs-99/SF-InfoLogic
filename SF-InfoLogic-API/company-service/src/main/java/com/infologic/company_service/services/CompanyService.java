package com.infologic.company_service.services;

import com.infologic.company_service.clients.CatalogoClient;
import com.infologic.company_service.entities.Company;
import com.infologic.company_service.exceptions.ResourceNotFoundException;
import com.infologic.company_service.repositories.CompanyRepository;
import com.infologic.company_service.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CatalogoClient catalogoClient;
    private Utils utils;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, CatalogoClient catalogoClient, Utils utils) {
        this.companyRepository = companyRepository;
        this.catalogoClient = catalogoClient;
        this.utils = utils;
    }

    public Company saveCompany(Company company){
        return companyRepository.save(company);

    }

    public List<Company> getAllCompanies(){
        //return companyRepository.findAll();

        List<Company> companies = companyRepository.findAll();

        // Obtener el nombre del departamento y agregarlo a cada objeto Company
        companies.forEach(company -> {

            if (company.getIdDeptoCompany() != null) {
                String nombreDepto = catalogoClient.getRcatalogosValue(company.getIdDeptoCompany());
                company.setNombreDeptoCompany(nombreDepto != null ? nombreDepto : "");
            } else {
                company.setNombreDeptoCompany("");
            }
            if (company.getIdMuniCompany() != null) {
                String nombreMunicipio = catalogoClient.getRcatalogosValue(company.getIdMuniCompany());
                company.setNombreMunicipioCompany(nombreMunicipio != null ? nombreMunicipio : "");
            } else {
                company.setNombreMunicipioCompany("");
            }

            if (company.getIdGiroCompany() != null) {
                String nombreGiro = catalogoClient.getRcatalogosValue(company.getIdGiroCompany());
                company.setNombreGiroCompany(nombreGiro != null ? nombreGiro : "");
            } else {
                company.setNombreGiroCompany("");
            }

            if (company.getIdRecinto() != null) {
                String nombreRecinto = catalogoClient.getRcatalogosValue(company.getIdRecinto());
                company.setNombreRecintoCompany(nombreRecinto != null ? nombreRecinto : "");
            } else {
                company.setNombreRecintoCompany("");
            }

            if (company.getIdRegimen() != null) {
                String nombreRegimen = catalogoClient.getRcatalogosValue(company.getIdRegimen());
                company.setNombreRegimenCompany(nombreRegimen != null ? nombreRegimen : "");
            } else {
                company.setNombreRegimenCompany("");
            }

        });

        return companies;
    }

    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElse(null);
    }


    public ResponseEntity<Object> deleteCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            companyRepository.delete(company.get());
        } else {
            throw new ResourceNotFoundException("No se encontró la empresa con ID " + id);
        }
        return null;
    }






}
