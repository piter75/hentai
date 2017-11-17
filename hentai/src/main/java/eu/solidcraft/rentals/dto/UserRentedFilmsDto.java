package eu.solidcraft.rentals.dto;

import lombok.Value;

import java.util.List;

@Value
public class UserRentedFilmsDto {
    List<RentedFilmDto> rentedFilms;
}
