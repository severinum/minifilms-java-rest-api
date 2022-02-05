package com.seweryn.minifilmsrestapi.film.service;

import com.seweryn.minifilmsrestapi.film.model.Film;
import com.seweryn.minifilmsrestapi.film.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAll() {
        List<Film> allFilm = filmRepository.findAll();

        // Example of some sort of business logic that can be implemented in the Service class (layer)
        allFilm.forEach(film -> {
                    film.setFilm_name(film.getFilm_name().toUpperCase(Locale.ROOT));
                });

        return allFilm;
    }

    public Optional<Film> getOne(String filmId) {
        return filmRepository.findById(filmId);
    }

    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }


    public void deleteFilm(String filmId) {
        filmRepository.deleteById(filmId);
    }

    /**
     * I've used stream to update film. If film exists it will update all fields,
     * if the film with given ID hasn't been found, it is assigning the ID
     * to the updated film data passed as JSON and save it to the DB.
     * This is just one use case. There is many way to do it. Each one is correct.
     *
     * @param filmId String - ID of the movie to be updated
     * @param updatedFilmData Film - updated film data
     */
    public void updateFilm(String filmId, Film updatedFilmData) {
        filmRepository.findById(filmId)
                .map( film -> {
                    film.setFilm_name(updatedFilmData.getFilm_name());
                    film.setFilm_type(updatedFilmData.getFilm_type());
                    film.setFilm_year(updatedFilmData.getFilm_year());
                    film.setFilm_link(updatedFilmData.getFilm_link());
                    return filmRepository.save(film);
                }).orElseGet(() -> {
                    updatedFilmData.setId(filmId);
                    return filmRepository.save(updatedFilmData);
                });
    }
}
