package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Efendi;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Efendi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EfendiRepository extends JpaRepository<Efendi,Long> {
    
}
