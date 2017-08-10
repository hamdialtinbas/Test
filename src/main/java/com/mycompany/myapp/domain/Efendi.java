package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Efendi.
 */
@Entity
@Table(name = "efendi")
public class Efendi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ack")
    private String ack;

    @OneToMany(mappedBy = "efendi",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Kole> koles = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Efendi name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAck() {
        return ack;
    }

    public Efendi ack(String ack) {
        this.ack = ack;
        return this;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public Set<Kole> getKoles() {
        return koles;
    }

    public Efendi koles(Set<Kole> koles) {

        for (Kole kole: koles
             ) {
            kole.setEfendi(this);
        }
        this.koles = koles;
        return this;
    }

    public Efendi addKole(Kole kole) {
        this.koles.add(kole);
        kole.setEfendi(this);
        return this;
    }

    public Efendi removeKole(Kole kole) {
        this.koles.remove(kole);
        kole.setEfendi(null);
        return this;
    }

    public void setKoles(Set<Kole> koles) {
        this.koles = koles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Efendi efendi = (Efendi) o;
        if (efendi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), efendi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Efendi{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", ack='" + getAck() + "'" +
            "}";
    }
}
