package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Person;
import com.mycompany.myapp.web.rest.util.SearchCriteria;

import java.util.List;

public interface IPersonDAO {

    void save(Person entity);

    List<Person> searchUser(List<SearchCriteria> params);
}
