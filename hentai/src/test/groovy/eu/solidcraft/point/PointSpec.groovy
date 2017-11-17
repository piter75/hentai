package eu.solidcraft.point

import eu.solidcraft.film.dto.FilmTypeDto
import eu.solidcraft.rentals.dto.FilmWasRented
import spock.lang.Specification

class PointSpec extends Specification {
    PointFacade facade = new PointConfiguration().testPointFacade()
    int userId = 1

    def "list should list user points"() {
        when:
            def userPoints = facade.list(userId)
        then:
            userPoints.number == 0

        and:
            facade.movieWasRented(new FilmWasRented(userId, FilmTypeDto.NEW))
        when:
            userPoints = facade.list(userId)
        then:
            userPoints.number == 2

        and:
            facade.movieWasRented(new FilmWasRented(userId, FilmTypeDto.REGULAR))
        when:
            userPoints = facade.list(userId)
        then:
            userPoints.number == 3

        and:
            facade.movieWasRented(new FilmWasRented(userId, FilmTypeDto.OLD))
        when:
            userPoints = facade.list(userId)
        then:
            userPoints.number == 4
    }
}
