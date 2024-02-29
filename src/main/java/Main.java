import controllers.AdvertController;
import controllers.UserController;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.AdvertRepository;
import repositories.UserRepository;
import repositories.interfaces.IAdvertRepository;
import repositories.interfaces.IUserRepository;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        // Initializing the database
        IDB db = new PostgresDB();

        // Creating repositories for users and adverts
        IUserRepository user_repo = new UserRepository(db);
        IAdvertRepository advert_repo = new AdvertRepository(db);

        // Creating controllers for users and adverts
        UserController user_controller = new UserController(user_repo);
        AdvertController advert_controller = new AdvertController(advert_repo);

        // Starting the real estate application with the created controllers
        RealEstateApp app = new RealEstateApp(user_controller, advert_controller);
        app.start();
    }
}