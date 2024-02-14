import controllers.UserController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final UserController controller;
    private final Scanner scanner;

    public MyApplication(UserController controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("Welcome to ShelterHunt Real Estate Agency Console Application!");
            System.out.println("Select option:");
            System.out.println("1. Get all users");
            System.out.println("2. Get user by id");
            System.out.println("3. Create user");
            System.out.println("4. Get owned adverts of user");
            System.out.println("0. Exit");
            System.out.println();
            try {
                System.out.print("Enter option (1-4): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    getAllUsersMenu();
                } else if (option == 2) {
                    getUserByIdMenu();
                } else if (option == 3) {
                    createUserMenu();
                } else if (option == 4) {
                    getOwnedAdvertsByUserIdMenu();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                scanner.nextLine(); // to ignore incorrect input
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");
        }
    }

    public void getAllUsersMenu() {
        String response = controller.getAllUsers();
        System.out.println(response);
    }

    public void getUserByIdMenu() {
        System.out.println("Please enter id");

        int id = scanner.nextInt();
        String response = controller.getUser(id);
        System.out.println(response);
    }

    public void createUserMenu() throws ParseException {
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter surname");
        String surname = scanner.next();
        System.out.println("Please enter gender (male/female)");
        String gender = scanner.next();

        System.out.println("Please enter date of birth (yyyy-mm-dd)");
        String date_of_birth = scanner.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date(dateFormat.parse(date_of_birth).getTime());

        String response = controller.createUser(name, surname, gender, date);
        System.out.println(response);
    }

    public void getOwnedAdvertsByUserIdMenu() {
        System.out.println("Please enter id of a user");

        int id = scanner.nextInt();
        String response = controller.getOwnedAdverts(id);
        System.out.println(response);
    }
}
