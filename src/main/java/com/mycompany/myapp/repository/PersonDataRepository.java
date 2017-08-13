package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDataRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

}
