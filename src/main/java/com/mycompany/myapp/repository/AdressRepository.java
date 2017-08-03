package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Adress;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Adress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdressRepository extends JpaRepository<Adress,Long> {
    
}
