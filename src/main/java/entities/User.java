package entities;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String surname;
    private boolean gender;
    private Date date_of_birth;
    private long phone_number;
    private ArrayList<Integer> owned_adverts_ids = new ArrayList<>();
    private ArrayList<Integer> fav_adverts_ids = new ArrayList<>();
    private ArrayList<Integer> reviews_ids = new ArrayList<>();

    private String username;
    private String password;

    public User(int id, String name, String surname, boolean gender, Date date_of_birth) {
        setId(id);
        setName(name);
        setSurname(surname);
        setGender(gender);
        setDate_of_birth(date_of_birth);
    }

    public User(String username, String password, String name, String surname, boolean gender, Date date_of_birth) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setSurname(surname);
        setGender(gender);
        setDate_of_birth(date_of_birth);
    }

    public boolean getGender() {  // I added this getter because lombok doesn't make one fsr.
        return gender;
    }


    @Override
    public String toString() {
        return "-[User profile]-" + '\n' +
                "Id:" + id + '\n' +
                "Name and surname: " + name + ' ' + surname + '\n' +
                "Gender: " + (getGender() ? "Male" : "Female") + '\n' +
                "Date of birth: " + date_of_birth + '\n' +
                "Phone number: " + phone_number + '\n' +
                "- Admin stuff -  " + '\n' +
                "owned_adverts_ids: " + owned_adverts_ids + '\n' +
                "fav_adverts_ids: " + fav_adverts_ids + '\n' +
                "review_ids: " + reviews_ids;
    }




}