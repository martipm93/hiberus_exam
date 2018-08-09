package org.hiberus.exam.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Style {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "styles")
    private Set<Artist> artists;

    public Style() {}

    public Style(String name) {
        this.name = name;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
    }

    @Override
    public String toString() {
        return String.format(
                "Style[id=%d, name='%s']",
                id, name);
    }
}
