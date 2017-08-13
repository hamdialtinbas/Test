package com.mycompany.myapp.web.rest;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.mycompany.myapp.domain.Person;
import com.mycompany.myapp.repository.IPersonDAO;
import com.mycompany.myapp.repository.PersonDataRepository;
import com.mycompany.myapp.web.rest.util.*;
import com.mycompany.myapp.web.rest.util.rsql.CustomRsqlVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/people")
public class PersonController {

    @Autowired
    private IPersonDAO service;

    @Autowired
    private PersonDataRepository dao;


    public PersonController() {
        super();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/people")
    @ResponseBody
    public List<Person> findAll(@RequestParam(value = "search", required = false) String search) {
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }
        return service.searchUser(params);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/people/spec")
    @ResponseBody
    public List<Person> findAllBySpecification(@RequestParam(value = "search") String search) {
        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
        String operationSetExper = Joiner.on("|")
            .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }

        Specification<Person> spec = builder.build();
        return dao.findAll(spec);
    }

    protected Specification<Person> resolveSpecificationFromInfixExpr(String searchParameters) {
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<Person> specBuilder = new GenericSpecificationsBuilder<>();
        return specBuilder.build(parser.parse(searchParameters), UserSpecification::new);
    }

    protected Specification<Person> resolveSpecification(String searchParameters) {

        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
        String operationSetExper = Joiner.on("|")
            .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\p{Punct}?)(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(searchParameters + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(5), matcher.group(4), matcher.group(6));
        }
        return builder.build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/people/rsql")
    @ResponseBody
    public List<Person> findAllByRsql(@RequestParam(value = "search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Person> spec = rootNode.accept(new CustomRsqlVisitor<Person>());
        return dao.findAll(spec);
    }
    @GetMapping(value = "/users/espec")
    @ResponseBody
    public List<Person> findAllByOrPredicate(@RequestParam(value = "search") String search) {
        Specification<Person> spec = resolveSpecification(search);
        return dao.findAll(spec);
    }

    @GetMapping(value = "/users/spec/adv")
    @ResponseBody
    public List<Person> findAllByAdvPredicate(@RequestParam(value = "search") String search) {
        Specification<Person> spec = resolveSpecificationFromInfixExpr(search);
        return dao.findAll(spec);
    }

}
