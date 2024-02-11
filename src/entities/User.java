package entities;

public class User {
    private int id;
    private String name;
    private String surname;
    private boolean gender;
    private int date_of_birth;
    private int phone_number;
    private int owned_adverts;
    private int fav_adverts;
    private int review_ids;

    public User(int id,
                String name,
                String surname,
                boolean gender,
                int date_of_birth,
                int phone_number,
                int owned_adverts,
                int fav_adverts,
                int review_ids) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this. gender = gender;
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
        this.owned_adverts = owned_adverts;
        this.fav_adverts = fav_adverts;
        this.review_ids = review_ids;
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
    public int getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(int date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    public int getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }
    public int getOwned_adverts() {
        return owned_adverts;
    }
    public void setOwned_adverts(int owned_adverts) {
        this.owned_adverts = owned_adverts;
    }
    public int getFav_adverts() {
        return fav_adverts;
    }
    public void setFav_adverts(int fav_adverts) {
        this.fav_adverts = fav_adverts;
    }
    public int getReview_ids() {
        return review_ids;
    }
    public void setReview_ids(int review_ids) {
        this.review_ids = review_ids;
    }

}
