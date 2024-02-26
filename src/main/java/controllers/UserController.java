package controllers;

import entities.Advert;
import repositories.interfaces.IAdvertRepository;
import repositories.interfaces.IUserRepository;
import entities.User;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final IUserRepository repo;
    private int currentIndex;

    public UserController(IUserRepository repo) {
        this.repo = repo;
        this.currentIndex = 0;
    }

    public User createUser(String username, String password, String name, String surname, String gender, String date_of_birth) throws ParseException {
        boolean male = gender.toLowerCase().equals("male");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date(dateFormat.parse(date_of_birth).getTime());
        User user = new User(username, password, name, surname, male, date);
        repo.createUser(user);
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


    public User getNextUser() {
        List<User> users = repo.getAllUsers();

        if (users.isEmpty()) {
            return null;
        }

        User nextUser = users.get(currentIndex);

        currentIndex++;

        if (currentIndex >= users.size()) {
            currentIndex = 0;
        }

        return nextUser;
    }
}

