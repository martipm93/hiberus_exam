package org.hiberus.exam.api;

import org.hiberus.exam.model.People;
import org.hiberus.exam.model.Style;
import org.hiberus.exam.persistance.PeopleRepository;
import org.hiberus.exam.persistance.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class StyleResource {

    @Autowired
    private StyleRepository styleRepository;

    @PostMapping("/style")
    public ResponseEntity<Void> createPeople(@RequestBody Style style) {
        Style savedStyle = styleRepository.save(style);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStyle.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
}
