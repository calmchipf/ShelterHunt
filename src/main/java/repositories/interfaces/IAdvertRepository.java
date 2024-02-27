package repositories.interfaces;

import entities.Advert;
import entities.Review;

import java.util.List;

public interface IAdvertRepository {
    boolean addAdvert(Advert advert, int userId);

    List<Review> checkReviews(int id);

    List<Advert> getAllAdverts();
}
