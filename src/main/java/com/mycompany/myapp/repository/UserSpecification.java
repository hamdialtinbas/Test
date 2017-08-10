package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Efendi;
import org.hibernate.Criteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification implements Specification<Efendi> {



    @Override
    public Predicate toPredicate(Root<Efendi> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
return null;
    }
}
