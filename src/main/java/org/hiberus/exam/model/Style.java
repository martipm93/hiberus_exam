package org.hiberus.exam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setId(Long id) { this.id = id; }

    public Long getId() { return this.id; }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
    }
}
