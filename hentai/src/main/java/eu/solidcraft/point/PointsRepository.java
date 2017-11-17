package eu.solidcraft.point;

import eu.solidcraft.point.dto.PointDto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

interface PointsRepository {
    void addUserPoints(int userId, int points);

    int find(int userId);
}

class InMemoryPointsRepository implements PointsRepository {
    private final int defaultValue = 0;
    private Map<Integer, Integer> store = new ConcurrentHashMap<>();

    @Override
    public void addUserPoints(int userId, int points) {
        store.put(userId, store.getOrDefault(userId, defaultValue) + points);
    }

    @Override
    public int find(int userId) {
        return store.getOrDefault(userId, defaultValue);
    }
}
