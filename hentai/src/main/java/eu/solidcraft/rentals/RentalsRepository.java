package eu.solidcraft.rentals;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

interface RentalsRepository {

    UserRentedFilms findFor(int userId);

    void save(UserRentedFilms userFilms);
}

class InMemoryRentalsRepository  implements RentalsRepository{

    Map<Integer, UserRentedFilms> store = new ConcurrentHashMap<>();

    @Override
    public UserRentedFilms findFor(int userId) {
        return store.getOrDefault(userId, new UserRentedFilms(userId));
    }

    @Override
    public void save(UserRentedFilms userFilms) {
        store.put(userFilms.getUserId(), userFilms);
    }
}

