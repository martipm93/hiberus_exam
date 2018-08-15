package org.hiberus.exam.api;

import org.hiberus.exam.model.Artist;
import org.hiberus.exam.model.People;
import org.hiberus.exam.persistance.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class PeopleResource {

    @Autowired
    private PeopleRepository peopleRepository;

    @PostMapping("/people")
    public ResponseEntity<Void> createPeople(@RequestBody People people) {
        People savedPeople = peopleRepository.save(people);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPeople.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
}
