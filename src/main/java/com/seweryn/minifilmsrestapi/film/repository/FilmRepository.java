package com.seweryn.minifilmsrestapi.film.repository;

import com.seweryn.minifilmsrestapi.film.model.Film;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilmRepository extends MongoRepository<Film, String> {
    // You can add here your custom methods if you need.
    // As default, MongoRepository and CRUDRepository delivers basic functionalities
    // and those are inherited as we are extending Mongo (and other) repositories.
}
