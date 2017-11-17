package eu.solidcraft.rentals.dto;

import lombok.Value;

import java.time.ZonedDateTime;

@Value
public class RentedFilmDto {
    String title;
    ZonedDateTime expectedReturnDate;
}
