package eu.solidcraft.base

import eu.solidcraft.film.dto.FilmDto
import eu.solidcraft.film.dto.FilmTypeDto

class FilmCreator {
    static def randomFilmWithType(FilmTypeDto filmType) {
        new FilmDto(UUID.randomUUID().toString(), filmType)
    }
}
