package eu.solidcraft.point;

import eu.solidcraft.point.dto.PointDto;
import eu.solidcraft.rentals.dto.FilmWasRented;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PointFacade {
    private PointsRepository repository;

    public PointDto list(int userId) {
        return new PointDto(repository.find(userId));
    }

    public void movieWasRented(FilmWasRented filmWasRented) {
        MovieTypePoints pointsForMovie = MovieTypePoints.valueOf(filmWasRented.getFilm().getType().name());

        repository.addUserPoints(filmWasRented.getUserId(), pointsForMovie.points);
    }
}

enum MovieTypePoints {
    NEW(2), REGULAR(1), OLD(1);

    final int points;

    MovieTypePoints(int i) {
        this.points = i;
    }
}