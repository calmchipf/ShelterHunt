package repositories;

import data.interfaces.IDB;
import entities.Review;
import repositories.interfaces.IAdvertRepository;

import java.util.List;

public class AdvertRepository implements IAdvertRepository {

    private final IDB db;

    public AdvertRepository(IDB db) {
        this.db = db;
    }

    @Override
    public List<Review> checkReviews(int id) {
        return null;
    }
    // Advert repo logic goes here...
}
