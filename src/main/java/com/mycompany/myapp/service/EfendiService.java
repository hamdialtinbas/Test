package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Efendi;

import java.util.List;
import java.util.Map;

public interface EfendiService {
    Efendi findById(long id) ;

    Efendi findByName(String name) ;

    void saveEfendi(Efendi Efendi);

    void updateEfendi(Efendi Efendi);

    void deleteEfendiById(long id);

    List<Efendi> findAllEfendis();

    void deleteAllEfendis();

    boolean isEfendiExist(Efendi efendi);

    List<Efendi> findEfendi(String name);


}
