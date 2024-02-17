package controllers;

import entities.Advert;
import entities.User;
import repositories.interfaces.IAdvertRepository;
import repositories.interfaces.IUserRepository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdvertController {
    private final IAdvertRepository repo;

    public AdvertController(IAdvertRepository repo) {
        this.repo = repo;
    }

    public User createAdvert(String username, String password, String name, String surname, String gender, String date_of_birth) throws ParseException {
        boolean male = gender.toLowerCase().equals("male");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date(dateFormat.parse(date_of_birth).getTime());
        User user = new User(username, password, name, surname, male, date);
        repo.createUser(user);
        return user;
    }

    public String getOwner(int id) {
        User user = repo.getUser(id);

        return (user == null ? "User was not found!" : user.toString());
    }

    public ArrayList<User> getAllReviews() {

        return (ArrayList<User>) repo.getAllUsers();
    }
}
