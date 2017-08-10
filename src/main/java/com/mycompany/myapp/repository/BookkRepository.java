package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Bookk;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Bookk entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookkRepository extends JpaRepository<Bookk,Long> {
    
}
