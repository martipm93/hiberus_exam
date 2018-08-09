package org.hiberus.exam.persistance;

import org.hiberus.exam.model.Artist;
import org.hiberus.exam.model.Style;
import org.springframework.data.repository.CrudRepository;

public interface StyleRepository extends CrudRepository<Style, Long> {

    Style findByName(String name);

}