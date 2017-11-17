package eu.solidcraft.rentals;

import eu.solidcraft.film.domain.FilmFacade;
import eu.solidcraft.film.dto.FilmDto;
import eu.solidcraft.rentals.dto.*;
import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
public class RentalsFacade {

    private final FilmFacade filmFacade;
    private RentalsRepository rentalsRepository;
    private RentalsEventPublisher rentalsEventPublisher;

    public UserRentedFilmsDto list(int userId) {
        UserRentedFilms userFilms = rentalsRepository.findFor(userId);
        return userFilms.toDto();
    }

    public RentalResultDto rentMovie(int userId, RentFilmRequest rentMovieRequest) {
        try {
            FilmDto filmToRent = filmFacade.show(rentMovieRequest.getTitle());

            UserRentedFilms userFilms = rentalsRepository.findFor(userId);
            ZonedDateTime rentingDate = ZonedDateTime.now();
            userFilms.rent(filmToRent.getTitle(), rentingDate, calculateReturnDate(rentingDate, rentMovieRequest.getRentDays()));
            rentalsRepository.save(userFilms);
            rentalsEventPublisher.filmWasRented(new FilmWasRented(userId, filmToRent));
            return new RentalResultDto(RentalResultStatus.SUCCESS, "");
        } catch (Exception e) {
            return new RentalResultDto(RentalResultStatus.FAILURE, e.getMessage());
        }
    }

    private ZonedDateTime calculateReturnDate(ZonedDateTime rentingDate, int rentDays) {
        return rentingDate.plusDays(rentDays);
    }
}
