package org.hiberus.exam.api;

import java.net.URI;
import java.util.*;

import org.hiberus.exam.model.Artist;
import org.hiberus.exam.model.People;
import org.hiberus.exam.model.Style;
import org.hiberus.exam.persistance.ArtistRepository;
import org.hiberus.exam.persistance.PeopleRepository;
import org.hiberus.exam.persistance.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ArtistResource {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private StyleRepository styleRepository;

    @GetMapping("/artists")
    public Set<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @GetMapping("/artists/{id}")
    public Artist getArtist(@PathVariable long id) throws Exception {
        Optional<Artist> artist = artistRepository.findById(id);

        if (!artist.isPresent())
            throw new Exception("id-" + id);

        return artist.get();
    }

    @DeleteMapping("/artists/{id}")
    public void deleteArtist(@PathVariable long id) {
        artistRepository.deleteById(id);
    }

    @PostMapping("/artists")
    public ResponseEntity<Void> createArtist(@RequestBody Artist artist) {
        Artist savedArtist = artistRepository.save(artist);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedArtist.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/artists/{id}")
    public ResponseEntity<Void> updateArtist(@RequestBody Artist artist, @PathVariable long id) {

        Optional<Artist> artistOptional = artistRepository.findById(id);

        if (!artistOptional.isPresent())
            return ResponseEntity.notFound().build();

        artist.setId(id);

        artistRepository.save(artist);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/artists/{id}/people/{idPeople}")
    public ResponseEntity<Void> putPeopleOnArtist(@PathVariable long id, @PathVariable long idPeople) {

        Optional<Artist> artistOptional = artistRepository.findById(id);

        if (!artistOptional.isPresent())
            return ResponseEntity.notFound().build();

        Optional<People> peopleOptional = peopleRepository.findById(idPeople);

        if (!peopleOptional.isPresent())
            return ResponseEntity.notFound().build();

        Artist artist = artistOptional.get();

        People people = peopleOptional.get();

        people.setArtist(artist);

        artist.addPeople(people);

        peopleRepository.save(people);

        artistRepository.save(artist);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/artists/{id}/styles/{idStyle}")
    public ResponseEntity<Void> putStylesOnArtist(@PathVariable long id, @PathVariable long idStyle) {

        Optional<Artist> artistOptional = artistRepository.findById(id);

        if (!artistOptional.isPresent())
            return ResponseEntity.notFound().build();

        Optional<Style> styleOptional = styleRepository.findById(idStyle);

        if (!styleOptional.isPresent())
            return ResponseEntity.notFound().build();

        Artist artist = artistOptional.get();

        Style style = styleOptional.get();

        style.addArtist(artist);

        artist.addStyle(style);

        styleRepository.save(style);

        artistRepository.save(artist);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/artists/style/{id}")
    public Set<Artist> getArtistByStyle(@PathVariable long id) {
        return artistRepository.findByStyle(id);
    }

}