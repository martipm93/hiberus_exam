package org.hiberus.exam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
public class Artist {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column
    private int year;

    @OneToMany(mappedBy="artist", cascade = CascadeType.ALL)
    private Set<People> people = new HashSet<People>();

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name = "artist_style",
            joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "style_id",
                    referencedColumnName = "id"))
    private Set<Style> styles = new HashSet<Style>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "artist_artist",
            joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "related_id",
                    referencedColumnName = "id"))
    private Set<Artist> related;



    public Artist() {}

    public Artist(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() { return this.name; }

    public Integer getYear() { return this.year; }

    public void setPeople(Set<People> people) {
        this.people = people;
    }

    public Set<People> getPeople() {
        return people;
    }

    public void addPeople(People people) {this.people.add(people);}

    public void setStyles(Set<Style> styles) {
        this.styles = styles;
    }

    public Set<Style> getStyles() {
        return styles;
    }

    public void addStyle(Style style) {this.styles.add(style);}

    public void addRelated(Artist artist) {this.related.add(artist);}

    public void setRelated(Set<Artist> related) {
        this.related = related;
    }

    public Set<Artist> getRelated() {
        return related;
    }

}