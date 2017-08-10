package com.mycompany.myapp.domain;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Bookk.
 */
@Entity
@Table(name = "bookk")
@NamedNativeQuery(name="getAllEmployees",query = "select * from catagory where id=?1",resultClass = Adress.class)
public class Bookk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "descripstion")
    private String descripstion;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private Catagory catagory;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Bookk name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripstion() {
        return descripstion;
    }

    public Bookk descripstion(String descripstion) {
        this.descripstion = descripstion;
        return this;
    }

    public void setDescripstion(String descripstion) {
        this.descripstion = descripstion;
    }

    public Catagory getCatagory() {
        return catagory;
    }

    public Bookk catagory(Catagory catagory) {
        this.catagory = catagory;
        return this;
    }

    public void setCatagory(Catagory catagory) {
        this.catagory = catagory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bookk bookk = (Bookk) o;
        if (bookk.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bookk.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Bookk{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", descripstion='" + getDescripstion() + "'" +
            "}";
    }
}
