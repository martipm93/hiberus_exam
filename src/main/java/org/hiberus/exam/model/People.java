package org.hiberus.exam.model;

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

    @ManyToOne(cascade = CascadeType.ALL)
    private Artist artist;

    public People() {}

    public People(String name, int years) {
        this.name = name;
        this.years = years;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return String.format(
                "People[id=%d, name='%s', years='%s']",
                id, name, years);
    }
}
