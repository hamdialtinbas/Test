package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Efendi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("EfendiService")
public class EfendiServiceImpl implements EfendiService{

    private static final AtomicLong counter = new AtomicLong();

    private static List<Efendi> efendiList;

    @PersistenceContext
    private EntityManager em;

    static{
        efendiList= populateDummyEfendis();
    }

    public List<Efendi> findAllEfendis() {
        return efendiList;
    }

    public Efendi findById(long id) {
        for(Efendi Efendi : efendiList){
            if(Efendi.getId() == id){
                return Efendi;
            }
        }
        return null;
    }

    public Efendi findByName(String name) {
        for(Efendi Efendi : efendiList){
            if(Efendi.getName().equalsIgnoreCase(name)){
                return Efendi;
            }
        }
        return null;
    }



    public void saveEfendi(Efendi Efendi) {
        Efendi.setId(counter.incrementAndGet());
        efendiList.add(Efendi);
    }

    public void updateEfendi(Efendi Efendi) {
        int index = efendiList.indexOf(Efendi);
        efendiList.set(index, Efendi);
    }

    public void deleteEfendiById(long id) {

        for (Iterator<Efendi> iterator = efendiList.iterator(); iterator.hasNext(); ) {
            Efendi Efendi = iterator.next();
            if (Efendi.getId() == id) {
                iterator.remove();
            }
        }
    }

    public boolean isEfendiExist(Efendi Efendi) {
        return findByName(Efendi.getName())!=null;
    }

    public void deleteAllEfendis(){
        efendiList.clear();
    }

    private static List<Efendi> populateDummyEfendis(){
        List<Efendi> Efendis = new ArrayList<Efendi>();

        return Efendis;
    }
    public List<Efendi> findEfendi(String name) {


        //Query query = em.createQuery("from Efendi as p where p.name=?");

       // return query.getResultList();
return null;

    }

}
