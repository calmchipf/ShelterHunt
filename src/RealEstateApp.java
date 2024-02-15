import java.util.Scanner;
import java.util.ArrayList;

class User {
    String username;
    String password;
    String name;
    String surname;
    String dateOfBirth;

    // Constructor
    public User(String username, String password, String name, String surname, String dateOfBirth) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }
}

class Advert {
    int id;
    String title;
    String description;
    // Add more properties as needed

    // Constructor
    public Advert(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}


