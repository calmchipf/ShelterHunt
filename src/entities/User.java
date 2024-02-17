package entities;

import java.sql.Date;
import java.util.ArrayList;


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

    public User(String username, String password, String name, String surname, Date date_of_birth){
        setUsername(username);
        setPassword(password);
        setName(name);
        setSurname(surname);
        setDate_of_birth(date_of_birth);
    }
    public User(int id,
                String name, String surname,
                boolean gender,
                Date date_of_birth,
                long phone_number,
                ArrayList<Integer> owned_adverts_ids,
                ArrayList<Integer> fav_adverts_ids,
                ArrayList<Integer> reviews_ids,
                String username, String password)
                {
        setId(id);
        setName(name);
        setSurname(surname);
        setGender(gender);
        setDate_of_birth(date_of_birth);
        setPhone_number(phone_number);
        setOwned_adverts(owned_adverts_ids);
        setFav_adverts(fav_adverts_ids);
        setReviews_id(reviews_ids);
        setUsername(username);
        setPassword(password);
    }

    public User(int id, String name, String surname, boolean gender, Date date_of_birth) {
        setId(id);
        setName(name);
        setSurname(surname);
        setGender(gender);
        setDate_of_birth(date_of_birth);
    }

    public User(String username, String password, String name, String surname, boolean male, Date dateOfBirth) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setSurname(surname);
        setGender(gender);
        setDate_of_birth(date_of_birth);
    }

    //Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean getGender() {
        return gender;
    }
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public long getPhone_number() {return phone_number;}
    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public ArrayList<Integer> getOwned_adverts() {
        return owned_adverts_ids;
    }
    public void setOwned_adverts(ArrayList<Integer> owned_adverts) {
        owned_adverts_ids = owned_adverts;
    }

    public ArrayList<Integer> getFav_adverts() {
        return fav_adverts_ids;
    }
    public void setFav_adverts(ArrayList<Integer> fav_adverts) {
        fav_adverts_ids = fav_adverts;
    }

    public ArrayList<Integer> getReview_ids() {
        return reviews_ids;
    }
    public void setReviews_id(ArrayList<Integer> reviews) {
        reviews_ids = reviews;
    }

    public void setUsername(String username) {this.username = username;}
    public String getUsername() {return username;}

    public void setPassword(String password) {this.password = password;}
    public String getPassword() {return password;}

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
