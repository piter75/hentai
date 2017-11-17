package eu.solidcraft.rentals;

import eu.solidcraft.film.domain.FilmFacade;
import org.springframework.context.annotation.Bean;

public class RentalsConfiguration {

    @Bean
    public RentalsFacade rentalsFacade(FilmFacade filmFacade, RentalsRepository rentalsRepository, RentalsEventPublisher eventPublisher) {
        return new RentalsFacade(filmFacade, rentalsRepository, eventPublisher);
    }
}
