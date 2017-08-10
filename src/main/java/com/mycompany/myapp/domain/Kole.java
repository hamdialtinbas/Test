package com.mycompany.myapp.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Kole.
 */
@Entity
@Table(name = "kole")
public class Kole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Efendi efendi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Kole name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Efendi getEfendi() {
        return efendi;
    }

    public Kole efendi(Efendi efendi) {
        this.efendi = efendi;
        return this;
    }

    public void setEfendi(Efendi efendi) {
        this.efendi = efendi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Kole kole = (Kole) o;
        if (kole.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), kole.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Kole{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
