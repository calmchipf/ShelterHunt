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

    static void login(Scanner scanner) {
        System.out.println("Please, enter your username and password.");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                currentUser = user;
                loggedInMenu(scanner);
                return;
            }
        }

        System.out.println("Inputted username or password is incorrect");
    }

    static void register(Scanner scanner) {
        System.out.println("Please, enter your data (username, pass, name, surname, date of birth).");
        System.out.print("Create a username: ");
        String username = scanner.nextLine();
        System.out.print("Create a password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter your date of birth (yyyy-mm-dd): ");
        String dateOfBirth = scanner.nextLine();

        User newUser = new User(username, password, name, surname, dateOfBirth);
        users.add(newUser);
        currentUser = newUser;

        loggedInMenu(scanner);
    }

    static void loggedInMenu(Scanner scanner) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("Hello, welcome to the application of our Real Estate Agency named ShelterHunt!");
            System.out.println("Select option:");
            System.out.println("1: Browse Adverts");
            System.out.println("2: My profile");
            System.out.println("3: Browse users");
            System.out.println("4: Add an advert");
            System.out.println("0: Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline



            switch (choice) {
                case 1:
                    browseAdverts(scanner);
                    break;
                case 2:
                    viewProfile();
                    break;
                case 3:
                    browseUsers(scanner);
                    break;
                case 4:
                    addAdvert(scanner);
                    break;
                case 0:
                    loggedIn = false;
                    currentUser = null;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void browseAdverts(Scanner scanner) {
        // Implement browsing adverts logic here
    }

    static void viewProfile() {
        // Implement viewing user's profile logic here
    }

    static void browseUsers(Scanner scanner) {
        // Implement browsing users logic here
    }

    static void addAdvert(Scanner scanner) {
        // Implement adding advert logic here
    }
}



