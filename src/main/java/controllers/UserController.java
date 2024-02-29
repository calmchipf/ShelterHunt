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

    // Constructor to initialize UserController with a user repository
    public UserController(IUserRepository repo) {
        this.repo = repo;
        this.currentIndex = 0;
    }

    // Method to create a new user based on provided data
    public User createUser(String username, String password, String name, String surname, String gender, String date_of_birth) throws ParseException {
        boolean male = gender.equalsIgnoreCase("male");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date(dateFormat.parse(date_of_birth).getTime());
        User user = new User(username, password, name, surname, male, date);
        repo.createUser(user);
        return repo.getLastUser();
    }

    // Method to get user information by ID
    public String getUser(int id) {
        User user = repo.getUser(id);

        return (user == null ? "User was not found!" : user.toString());
    }

    public String getLastUser(){
        User user = repo.getLastUser();

        return (user == null ? "User was not found!" : user.toString());
    }

    // Method to retrieve all users from the repository
    public ArrayList<User> getAllUsers() {

        return (ArrayList<User>) repo.getAllUsers();
    }

    // Method to retrieve adverts owned by a specific user
    public String getOwnedAdverts(int id) {
        List<Advert> adverts = repo.getOwnedAdverts(id);

        StringBuilder response = new StringBuilder();
        for (Advert advert : adverts) {
            response.append(advert.toString()).append("\n");
        }

        return response.toString();
    }

    public void updateUser(int id, String choice, String new_info){
        repo.updateUser(id, choice, new_info);
    }

    public void deleteUser(int id){
        repo.deleteUser(id);
    }

    // Method to retrieve the next user in the list of users
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

