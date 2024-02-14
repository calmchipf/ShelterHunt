package entities;

import java.sql.Array;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class User {
    private int id;
    private String name;
    private String surname;
    private boolean gender;
    private Date date_of_birth;
    private int phone_number;
    private Integer[] owned_adverts_ids = {};
    private Integer[] fav_adverts_ids = {};
    private Integer[] review_ids = {};

    public User(int id){
        setId(id);
    }
    public User(int id,
                String name,
                String surname,
                boolean gender,
                Date date_of_birth,
                int phone_number) {
        setId(id);
        setName(name);
        setSurname(surname);
        setGender(gender);
        setDate_of_birth(date_of_birth);
        setPhone_number(phone_number);
    }

    public User(String name, String surname, boolean gender, Date date_of_birth) {
        setName(name);
        setSurname(surname);
        setGender(gender);
        setDate_of_birth(date_of_birth);
    }

    public User(int id, String name, String surname, boolean gender, Date date_of_birth) {
        setId(id);
        setName(name);
        setSurname(surname);
        setGender(gender);
        setDate_of_birth(date_of_birth);
    }

    public User(int id, String name, String surname, boolean gender, Date date_of_birth, Integer[] owned_adverts_ids) {
        setId(id);
        setName(name);
        setSurname(surname);
        setGender(gender);
        setDate_of_birth(date_of_birth);
        setOwned_adverts(owned_adverts_ids);
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
    public int getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }
    public Integer[] getOwned_adverts() {
        return owned_adverts_ids;
    }
    public void setOwned_adverts(Integer[] owned_adverts) {
        owned_adverts_ids = owned_adverts;
    }
    public Integer[] getFav_adverts() {
        return fav_adverts_ids;
    }
    public void setFav_adverts(Integer[] fav_adverts) {
        fav_adverts_ids = fav_adverts;
    }
    public Integer[] getReview_ids() {
        return review_ids;
    }
    public void addReview_id(Integer[] reviews) {
        review_ids = reviews;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                ", date_of_birth=" + date_of_birth +
                ", phone_number=" + phone_number +
                ", owned_adverts_ids=" + Arrays.toString(owned_adverts_ids) +
                ", fav_adverts_ids=" + fav_adverts_ids +
                ", review_ids=" + review_ids +
                '}';
    }
}
