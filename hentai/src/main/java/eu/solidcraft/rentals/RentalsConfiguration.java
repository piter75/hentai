package eu.solidcraft.rentals;

import eu.solidcraft.film.domain.FilmFacade;
import org.springframework.context.annotation.Bean;

public class RentalsConfiguration {

    @Bean
    public RentalsFacade rentalsFacade(FilmFacade filmFacade, RentalsRepository rentalsRepository) {
        return new RentalsFacade(filmFacade, rentalsRepository);
    }
}
