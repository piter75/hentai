package eu.solidcraft.rentals;

import eu.solidcraft.rentals.dto.RentedFilmDto;
import eu.solidcraft.rentals.dto.UserRentedFilmsDto;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class UserRentedFilms {

    private final int userId;
    private final List<RentedFilm> rentedFilms;

    public UserRentedFilms(int userId) {
        this.userId = userId;
        rentedFilms = new ArrayList<>();
    }

    void rent(String title, ZonedDateTime rentingDate, ZonedDateTime returnDate) {
        rentedFilms.add(new RentedFilm(title, rentingDate, returnDate));
    }

    public int getUserId() {
        return userId;
    }

    public UserRentedFilmsDto toDto() {
        List<RentedFilmDto> rentedMoviesDto = rentedFilms.stream().map(RentedFilm::toDto).collect(Collectors.toList());
        return new UserRentedFilmsDto(rentedMoviesDto);
    }
}
