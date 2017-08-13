package com.mycompany.myapp.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Adress.
 */
@Entity
@Table(name = "adress")
public class Adress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "l_11")
    private Boolean l11;

    @Column(name = "l_16")
    private Integer l16;

    @Column(name = "l_22")
    private Integer l22;

    @Column(name = "l_11_b")
    private Integer l11b;

    @Column(name = "l_1")
    private Integer l1;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Adress name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isl11() {
        return l11;
    }

    public Adress l11(Boolean l11) {
        this.l11 = l11;
        return this;
    }

    public void setl11(Boolean l11) {
        this.l11 = l11;
    }

    public Integer getl16() {
        return l16;
    }

    public Adress l16(Integer l16) {
        this.l16 = l16;
        return this;
    }

    public void setl16(Integer l16) {
        this.l16 = l16;
    }

    public Integer getl22() {
        return l22;
    }

    public Adress l22(Integer l22) {
        this.l22 = l22;
        return this;
    }

    public void setl22(Integer l22) {
        this.l22 = l22;
    }

    public Integer getl11b() {
        return l11b;
    }

    public Adress l11b(Integer l11b) {
        this.l11b = l11b;
        return this;
    }

    public void setl11b(Integer l11b) {
        this.l11b = l11b;
    }

    public Integer getl1() {
        return l1;
    }

    public Adress l1(Integer l1) {
        this.l1 = l1;
        return this;
    }

    public void setl1(Integer l1) {
        this.l1 = l1;
    }

    public Person getPerson() {
        return person;
    }

    public Adress person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Adress adress = (Adress) o;
        if (adress.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adress.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Adress{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", l11='" + isl11() + "'" +
            ", l16='" + getl16() + "'" +
            ", l22='" + getl22() + "'" +
            ", l11b='" + getl11b() + "'" +
            ", l1='" + getl1() + "'" +
            "}";
    }
}
