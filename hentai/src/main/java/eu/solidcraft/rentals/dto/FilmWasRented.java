package eu.solidcraft.rentals.dto;

import eu.solidcraft.film.dto.FilmDto;
import lombok.Value;

@Value
public class FilmWasRented {
    int userId;
    FilmDto film;
}
