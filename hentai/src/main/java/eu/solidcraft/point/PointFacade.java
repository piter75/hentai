package eu.solidcraft.point;

import eu.solidcraft.point.dto.PointDto;
import eu.solidcraft.rent.dto.MovieWasRented;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PointFacade {
    private PointsRepository repository;

    public PointDto list(int userId) {
        return new PointDto(repository.find(userId));
    }

    public void movieWasRented(MovieWasRented movieWasRented) {
        MovieTypePoints pointsForMovie = MovieTypePoints.valueOf(movieWasRented.getFilmType().name());

        repository.addUserPoints(movieWasRented.getUserId(), pointsForMovie.points);
    }
}

enum MovieTypePoints {
    NEW(2), REGULAR(1), OLD(1);

    final int points;

    MovieTypePoints(int i) {
        this.points = i;
    }
}