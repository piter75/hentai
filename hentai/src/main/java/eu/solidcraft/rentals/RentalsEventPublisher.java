package eu.solidcraft.rentals;

import eu.solidcraft.rentals.dto.FilmWasRented;

public interface RentalsEventPublisher {
    void filmWasRented(FilmWasRented movieWasRented);
}
