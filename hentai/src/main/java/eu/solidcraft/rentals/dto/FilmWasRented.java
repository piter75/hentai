package eu.solidcraft.rentals.dto;

import eu.solidcraft.film.dto.FilmTypeDto;
import lombok.Value;

@Value
public class FilmWasRented {
    int userId;
    FilmTypeDto filmType;
}
