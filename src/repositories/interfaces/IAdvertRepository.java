package repositories.interfaces;

import entities.Review;

import java.util.List;

public interface IAdvertRepository {
    List<Review> checkReviews(int id);

}
