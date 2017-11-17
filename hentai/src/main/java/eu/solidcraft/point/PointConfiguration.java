package eu.solidcraft.point;

public class PointConfiguration {

    public PointFacade testPointFacade() {
        PointsRepository repository = new InMemoryPointsRepository();
        return new PointFacade(repository);
    }
}
