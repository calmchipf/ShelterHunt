import controllers.AdvertController;
import controllers.UserController;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.AdvertRepository;
import repositories.UserRepository;
import repositories.interfaces.IAdvertRepository;
import repositories.interfaces.IUserRepository;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        IDB db = new PostgresDB();
        IUserRepository user_repo = new UserRepository(db);
        IAdvertRepository advert_repo = new AdvertRepository(db);

        UserController user_controller = new UserController(user_repo);
        AdvertController advert_controller = new AdvertController(advert_repo);

        RealEstateApp app = new RealEstateApp(user_controller, advert_controller);
        app.start();
    }
}