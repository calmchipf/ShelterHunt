package controllers;

import entities.Advert;
import repositories.interfaces.IUserRepository;
import entities.User;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    public User createUser(String username, String password, String name, String surname, String gender, String date_of_birth) throws ParseException {
        boolean male = gender.toLowerCase().equals("male");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date(dateFormat.parse(date_of_birth).getTime());
        User user = new User(username, password, name, surname, male, date);
        boolean created = repo.createUser(user);
        return user;
    }

    public String getUser(int id) {
        User user = repo.getUser(id);

        return (user == null ? "User was not found!" : user.toString());
    }

    public ArrayList<User> getAllUsers() {

        return (ArrayList<User>) repo.getAllUsers();
    }

    public Object getOwnedAdverts(int id) {
        List<Advert> adverts = repo.getOwnedAdverts(id);

        StringBuilder response = new StringBuilder();
        for (Advert advert : adverts) {
            response.append(advert.toString()).append("\n");
        }

        return response.toString();
    }
}

