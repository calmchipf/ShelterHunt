import controllers.AdvertController;
import controllers.UserController;
import data.PostgresDB;
import data.interfaces.IDB;
import entities.Advert;
import entities.User;
import repositories.AdvertRepository;

import java.text.ParseException;
import java.util.InputMismatchException;
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
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
            System.out.println("Hello, welcome to the application of our Real Estate Agency named ShelterHunt!");
            System.out.println("Select option:");
            System.out.println("1: Browse Adverts");
            System.out.println("2: My profile");
            System.out.println("3: Browse users");
            System.out.println("4: Add an advert");
            System.out.println("0: Exit");
            System.out.print("Your choice: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
                continue; // Skip the rest of the loop and start from the beginning
            }

            switch (choice) {
                case 1:
                    browseAdverts(scanner);
                    break;
                case 2:
                    if (viewProfile() == false){
                        loggedIn = false;
                        currentUser = null;
                    }
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

    static void myAdverts(){
        String response = user_controller.getOwnedAdverts(currentUser.getId());
        System.out.println(response);
    }

    // Method to view the profile of the currently logged-in user
    static boolean viewProfile() {
        String response = user_controller.getUser(currentUser.getId());
        System.out.println(response);
        boolean loggedIn = true;
        while (loggedIn){
            System.out.println("----------------------");
            System.out.println("This is your profile!");
            System.out.println("Select option:");
            System.out.println("1: My adverts");
            System.out.println("2: Change profile info");
            System.out.println("3: Log out");
            System.out.println("4: Delete account");
            System.out.println("0: Back to main menu");
            System.out.print("Your choice: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next(); // Consume the invalid input
                choice = -1;
            }
            switch (choice) {
                case 1:
                    myAdverts();
                    break;
                case 2:
                    changeProfileInfo();
                    break;
                case 3:
                    loggedIn = false;
                    currentUser = null;
                    break;
                case 4:
                    deleteAccount();
                    loggedIn = false;
                    currentUser = null;
                    break;
                case 0:
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        return loggedIn;
    }

    static void deleteAccount(){
        int current_id = currentUser.getId();
        user_controller.deleteUser(current_id);
    }

    static void changeProfileInfo() {
        int current_id = currentUser.getId();
        String new_info = null;
        System.out.println("- Edit profile -");
        System.out.println("Select option:");
        System.out.println("1: Change name");
        System.out.println("2: Change surname");
        System.out.println("3: Change gender");
        System.out.println("4: Change date of birth");
        System.out.println("5: Change phone number");
        System.out.println("6: Change username");
        System.out.println("7: Change password");
        System.out.println("0: Cancel");
        System.out.print("Your choice: ");
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next(); // Consume the invalid input
            choice = -1;
        }
        scanner.nextLine(); // Consume the newline character

        if ((choice < 7) && (choice > 0)) {
            System.out.println("Enter new info:");
            new_info = scanner.nextLine();
        }
        switch (choice) {
            case 1:
                user_controller.updateUser(current_id, "name", new_info);
                break;
            case 2:
                user_controller.updateUser(current_id, "surname", new_info);
                break;
            case 3:
                user_controller.updateUser(current_id, "gender", new_info);
                break;
            case 4:
                user_controller.updateUser(current_id, "date_of_birth", new_info);
                break;
            case 5:
                user_controller.updateUser(current_id, "phone_number", new_info);
                break;
            case 6:
                user_controller.updateUser(current_id, "username", new_info);
                break;
            case 7:
                user_controller.updateUser(current_id, "password", new_info);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice. You're back to your profile.");
        }

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

        int price = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Enter price:");
                price = scanner.nextInt();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid price.");
                // Consume invalid input
                scanner.nextLine();
            }
        }

        // Consume newline left-over
        scanner.nextLine();

        System.out.println("Enter description:");
        String description = scanner.nextLine();

        // Creating an Advert object
        Advert advert = new Advert(address, price, description);

        // Get the logged-in user's ID
        int userId = currentUser.getId();

        boolean success = advert_controller.addAdvert(advert, userId);

        if (success) {
            System.out.println("Advert added successfully!");
        } else {
            System.out.println("Failed to add advert.");
        }
    }
}



