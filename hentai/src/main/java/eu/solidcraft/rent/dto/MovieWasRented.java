package eu.solidcraft.rent.dto;

import eu.solidcraft.film.dto.FilmTypeDto;
import lombok.Value;

@Value
public class MovieWasRented {
    int userId;
    FilmTypeDto filmType;
}
