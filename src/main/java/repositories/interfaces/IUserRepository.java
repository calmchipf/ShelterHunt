package repositories.interfaces;

import entities.Advert;
import entities.Review;
import entities.User;
import java.util.List;
public interface IUserRepository {
    boolean createUser(User user);
    User getUser(int id);
    List<User> getAllUsers();
    List<Advert> getOwnedAdverts(int id);

    User getUserByUsernameAndPassword(String username, String password);

    List<Review> getMadeReviews();

}
