package org.hiberus.exam.persistance;

import org.hiberus.exam.model.People;
import org.springframework.data.repository.CrudRepository;

public interface PeopleRepository extends CrudRepository<People, Long> {

    People findByName(String name);

}
