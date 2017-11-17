package eu.solidcraft.rentals;

import eu.solidcraft.rentals.dto.RentedFilmDto;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
public class RentedFilm {
    String title;
    ZonedDateTime expectedReturnDate;
    ZonedDateTime rentingDate;

    public RentedFilmDto toDto() {
        return new RentedFilmDto(title, expectedReturnDate);
    }
}
