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

public class RealEstateApp {
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Advert> adverts = new ArrayList<>();
    static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Enter 'login' or 'register' in order to access the app: ");
            String input = scanner.nextLine();

            if (input.equals("login")) {
                login(scanner);
            } else if (input.equals("register")) {
                register(scanner);
            } else {
                System.out.println("Invalid input. Please enter 'login' or 'register'.");
            }
        }
    }


