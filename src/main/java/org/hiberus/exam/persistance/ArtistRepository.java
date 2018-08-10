package org.hiberus.exam.persistance;

import org.hiberus.exam.model.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ArtistRepository extends CrudRepository<Artist, Long> {

    @Override
    Set<Artist> findAll();

    @Query("select a from Artist a inner join fetch a.styles s where s.name = :nameStyle")
    Set<Artist> findByStyle(@Param("nameStyle") String nameStyle);

    Artist findByName(String name);

}
