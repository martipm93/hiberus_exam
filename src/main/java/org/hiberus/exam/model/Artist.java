package org.hiberus.exam.model;

import javax.persistence.*;
import java.util.HashSet;
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
            joinColumns = @JoinColumn(name = "style_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id",
                    referencedColumnName = "id"))
    private Set<Style> styles = new HashSet<Style>();

//    @ManyToMany(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    @JoinTable(name = "artist_artist",
//            joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "artist_id",
//                    referencedColumnName = "id"))
//    private List<Artist> artists;

    public Artist() {}

    public Artist(String name, int year) {
        this.name = name;
        this.year = year;
    }

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

    @Override
    public String toString() {
        return String.format(
                "Artist[id=%d, name='%s', year='%s']",
                id, name, year);
    }

}