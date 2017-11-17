package eu.solidcraft.rentals.dto;

import lombok.Value;

@Value
public class RentFilmRequest {
    private final String title;
    private int rentDays;
}
