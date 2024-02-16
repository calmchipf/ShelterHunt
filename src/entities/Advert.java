package entities;

import java.sql.Array;
import java.util.ArrayList;

public class Advert {
    private int id;
    private String address;
    private String location;
    private int price;
    private String description;
    private ArrayList<Integer> photos_ids = new ArrayList<>();
    private ArrayList<Integer> review_ids = new ArrayList<>();

    public Advert(int id, String address, String location, int price, String description){
        setId(id);
        setAddress(address);
        setLocation(location);
        setPrice(price);
        setDescription(description);
    }

    public Advert(int id){
        setId(id);
    }

    public Advert(int id, String address, String location, int price, String description, ArrayList<Integer> photos_ids){
        setId(id);
        setAddress(address);
        setLocation(location);
        setPrice(price);
        setDescription(description);
        setPhotos_ids(photos_ids);
    }
    // Getters and setters
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public ArrayList<Integer> getPhotos_ids(){
        return photos_ids;
    }

    public void setPhotos_ids(ArrayList<Integer> photos_ids){
        this.photos_ids = photos_ids;
    }
    public void addPhoto (int photos_id){
        photos_ids.add(photos_id);
    }
    public ArrayList<Integer> getReview_ids(){
        return review_ids;
    }
    public void addReview_id(int review_id){
        review_ids.add(review_id);
    }

    @Override
    public String toString() {
        return "[ID : " + id + "]" + "Address: " + address + " | Price: " + price +
                " tenge | Desc: " + description + " ||| photos: " + photos_ids + " | reviews: " + review_ids;
    }
}
