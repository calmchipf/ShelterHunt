import controllers.AdvertController;
import controllers.UserController;
import data.PostgresDB;
import data.interfaces.IDB;
import entities.Advert;
import entities.User;
import repositories.AdvertRepository;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class RealEstateApp {
    // Controllers for handling user and advert functionalities
    private static UserController user_controller;
    private static AdvertController advert_controller;

    // Scanner object for taking user input
    private static Scanner scanner = null;

    // Lists to hold users and adverts data
    static ArrayList<User> users = new ArrayList<User>();
    static ArrayList<Advert> adverts = new ArrayList<>();

    // Current logged-in user
    static User currentUser;

    // Constructor to initialize RealEstateApp with UserController and AdvertController
    public RealEstateApp(UserController user_controller, AdvertController advert_controller) {
        this.user_controller = user_controller;
        this.advert_controller = advert_controller;
        // Initialize scanner for user input
        scanner = new Scanner(System.in);
    }

    // It prompts the user to log in or register and handles user input accordingly
    public static void start() throws ParseException {
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

    // Method to handle user login process
    static void login(Scanner scanner) {
        users = user_controller.getAllUsers();

        System.out.println("Please, enter your username and password.");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                // Proceed to logged-in menu
                loggedInMenu();
                return;
            }
        }

        System.out.println("Inputted username or password is incorrect");
    }

    // Method to handle user registration process
    static void register(Scanner scanner) throws ParseException {
        System.out.println("Please, enter your data (username, pass, name, surname, date of birth).");
        System.out.print("Create a username: ");
        String username = scanner.nextLine();
        System.out.print("Create a password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your surname: ");
        String surname = scanner.nextLine();
        System.out.println("Enter your gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter your date of birth (yyyy-mm-dd): ");
        String date_of_birth = scanner.nextLine();

        User newUser = user_controller.createUser(username, password, name, surname, gender, date_of_birth);
        currentUser = newUser;

        // Proceed to logged-in menu
        loggedInMenu();
    }

    // It provides options to browse adverts, view profile, browse users, and add an advert
    static void loggedInMenu() {
        users = user_controller.getAllUsers();
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

            // Consume newline

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

    // Method to browse adverts available
    static void browseAdverts(Scanner scanner) {
        System.out.println("Browse Adverts:");
        boolean continueBrowsing = true;
        while (continueBrowsing) {
            Advert nextAdvert = advert_controller.getNextAdvert();
            if (nextAdvert != null) {
                System.out.println(nextAdvert.toString());
                System.out.println("Press Enter to see the next advert or type 'exit' to quit browsing:");
                String input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    continueBrowsing = false;
                }
            } else {
                System.out.println("No more adverts available.");
                continueBrowsing = false;
            }
        }
    }

    // Method to view the profile of the currently logged-in user
    static void viewProfile() {
        String response = user_controller.getUser(currentUser.getId());
        System.out.println("[][][] The id is : " + currentUser.getId());
        System.out.println(response);
    }

    // Method to browse users registered in the system
    static void browseUsers(Scanner scanner) {
        System.out.println("Browse Users:");
        boolean continueBrowsing = true;
        while (continueBrowsing) {
            User nextUser = user_controller.getNextUser();
            if (nextUser != null) {
                System.out.println(nextUser.toString());
                System.out.println("Press Enter to see the next advert or type 'exit' to quit browsing:");
                String input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    continueBrowsing = false;
                }
            } else {
                System.out.println("No more users available.");
                continueBrowsing = false;
            }
        }
    }

    // Method to add a new advert
    static void addAdvert(Scanner scanner) {
        System.out.println("Enter address:");
        String address = scanner.nextLine();

        System.out.println("Enter location:");
        String location = scanner.nextLine();

        System.out.println("Enter price:");
        int price = scanner.nextInt();
        // Consume newline left-over
        scanner.nextLine();

        System.out.println("Enter description:");
        String description = scanner.nextLine();

        // Creating an Advert object

        Advert advert = new Advert(address, location, price, description);

        // Repository object initialized somewhere
        IDB db = new PostgresDB();
        AdvertRepository advertRepository = new AdvertRepository(db);

        // Get the logged-in user's ID
        int userId = 1;

        boolean success = advertRepository.addAdvert(advert, userId);

        if (success) {
            System.out.println("Advert added successfully!");
        } else {
            System.out.println("Failed to add advert.");
        }
    }
}



