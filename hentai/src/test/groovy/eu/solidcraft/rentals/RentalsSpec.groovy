package eu.solidcraft.rentals

import eu.solidcraft.film.domain.FilmFacade
import eu.solidcraft.film.dto.FilmDto
import eu.solidcraft.film.dto.FilmTypeDto
import eu.solidcraft.rentals.dto.FilmWasRented
import eu.solidcraft.rentals.dto.RentFilmRequest
import eu.solidcraft.rentals.dto.RentalResultDto
import eu.solidcraft.rentals.dto.RentalResultStatus
import eu.solidcraft.rentals.dto.RentedFilmDto
import eu.solidcraft.rentals.dto.UserRentedFilmsDto
import spock.lang.Specification

import java.time.ZonedDateTime

class RentalsSpec extends Specification {
    def filmFacade = Mock(FilmFacade)
    def eventPublisher = Mock(RentalsEventPublisher)
    def rentalFacade = new RentalsConfiguration().rentalsFacade(filmFacade, new InMemoryRentalsRepository(), eventPublisher)
    def film = new FilmDto("First film ever", FilmTypeDto.NEW)
    def film2 = new FilmDto("Some great fantasy movie", FilmTypeDto.NEW)
    def userId = 1
    def rentedFilm = new RentedFilmDto(film.title, ZonedDateTime.now())
    def rentedFilm2 = new RentedFilmDto(film2.title, ZonedDateTime.now().plusDays(1))
    def notExistingMovieTitle = "Not existing movie"

    def "cannot rent not existing movie"() {
        given:
            filmFacade.show(notExistingMovieTitle) >> { throw new RuntimeException("Film not found") }

        when:
            RentalResultDto rentalResult = rentalFacade.rentMovie(userId, new RentFilmRequest(notExistingMovieTitle, 1))

        then:
            rentalResult.rentalResultStatus == RentalResultStatus.FAILURE
    }

    def "should list rented movies"() {
        given:
            filmFacade.show(film.title) >> film
            filmFacade.show(film2.title) >> film2
            rentalFacade.rentMovie(userId, new RentFilmRequest(film.title, 1))
            rentalFacade.rentMovie(userId, new RentFilmRequest(film2.title, 2))

        when:
            UserRentedFilmsDto userRentedFilms = rentalFacade.list(userId)

        then:
            userRentedFilms.rentedFilms*.title as Set == [rentedFilm, rentedFilm2]*.title as Set
    }

    def "renting film should publish event"() {
        when:
            filmFacade.show(film.title) >> film
            rentalFacade.rentMovie(userId, new RentFilmRequest(film.title, 1))
        then:
            1 * eventPublisher.filmWasRented(new FilmWasRented(userId, new FilmDto(film.title, film.type)))
    }
}
