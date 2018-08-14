package org.hiberus.exam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Style {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "styles")
    private Set<Artist> artists;

    public Style() {}

    public Style(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
    }
}
