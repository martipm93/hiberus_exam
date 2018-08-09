package org.hiberus.exam;

import org.hiberus.exam.model.Artist;
import org.hiberus.exam.model.People;
import org.hiberus.exam.model.Style;
import org.hiberus.exam.persistance.ArtistRepository;
import org.hiberus.exam.persistance.CustomerRepository;
import org.hiberus.exam.persistance.PeopleRepository;
import org.hiberus.exam.persistance.StyleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamApplication {

    private static final Logger log = LoggerFactory.getLogger(ExamApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class);
    }

    @Bean
    public CommandLineRunner demo(ArtistRepository artistRepository, PeopleRepository peopleRepository, StyleRepository styleRepository) {
        return (args) -> {

            //Create registers of Artists:
            Artist avenged = new Artist("Avenged Sevenfold", 1999);
            Artist nothingMore = new Artist("Nothing More", 2003);

            Style heavy = new Style("Heavy Metal");
            Style metalcore = new Style("Metalcore");
            Style hardrock = new Style("Hard Rock");

            People mshadows = new People("M Shadows", 37);
            People synyster = new People("Synyster Gates", 37);


            avenged.setPeople(new HashSet<People>(){{
                add(mshadows);
                add(synyster);
            }});
            avenged.setStyles(new HashSet<Style>(){{
                add(heavy);
                add(metalcore);
            }});

            nothingMore.setStyles(new HashSet<Style>(){{
                add(hardrock);
            }});

            artistRepository.save(avenged);
            artistRepository.save(nothingMore);

            //Create registers of Styles:

            styleRepository.save(heavy);
            styleRepository.save(metalcore);
            styleRepository.save(hardrock);
//
//            //Create registers of People
//
            peopleRepository.save(mshadows);
            peopleRepository.save(synyster);

            log.info("");
            log.info("Welcome to the Hiberus exam.");
            log.info("-------------------------------");
            log.info("");

            log.info("List of Artists:");
            log.info("-------------------------------");
            Set<Artist> artists = artistRepository.findAll();
            for (Artist artist : artists) {
                log.info(artist.toString());
            }
            log.info("");
            log.info("Upload a new artist");
            log.info("-------------------------------");
            artistRepository.save(new Artist("Pantera", 1981));
            log.info("Check the upload:");
            log.info("--------------------------------");
            for (Artist artist : artistRepository.findAll()) {
                log.info(artist.toString());
            }
            log.info("");

            log.info("Assign people to artists. For example assing a person to Nothing More band:");
            log.info("--------------------------------");
            People jonny = new People("Jonny Hawkins", 31);
            peopleRepository.save(jonny);

            nothingMore.addPeople(jonny);
            artistRepository.save(nothingMore);
            log.info(jonny.toString());

            log.info("");

            log.info("Show artists by a given style. For example for Metalcore style, we get:");
            log.info("--------------------------------");
            Set<Artist> artistsStyle = artistRepository.findByStyle("Metalcore");
            for (Artist a : artistsStyle) {
                log.info(a.toString());
            }

            log.info("");
        };
    }

}