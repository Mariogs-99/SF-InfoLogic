package com.infologic.company_service.repositories;

import com.infologic.company_service.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>
{

    @Transactional
    @Modifying
    @Query("UPDATE Company c SET c.deletedAt = :deletedAt WHERE c.idCompany = :id")
    int softDeleteCompanyById(Long id, Timestamp deletedAt);

}

