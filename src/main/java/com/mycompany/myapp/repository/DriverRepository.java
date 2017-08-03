package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Driver;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Driver entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    
}
