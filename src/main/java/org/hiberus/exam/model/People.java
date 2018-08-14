package org.hiberus.exam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class People {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int years;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Artist artist;

    public People() {}

    public People(String name, int years) {
        this.name = name;
        this.years = years;
    }

    public String getName() {
       return name;
    }

    public Integer getYears() {
        return years;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
