package entities;

import java.util.Arrays;

public class Adverts {
    private int id;
    private String address;
    private String location;
    private int price;
    private String description;
    private int[] photos_ids;
    private int[] review_ids;

    //Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int[] getPhotos_ids() { return photos_ids; }
    public void setPhotos_ids(int[] photos_ids) { this.photos_ids = photos_ids; }
    public int[] getReview_ids() { return review_ids; }
    public void setReview_ids(int[] review_ids) { this.review_ids = review_ids; }

    @Override
    public String toString() {
        return "Adverts{" +
                "ID = " + id +
                ", Address = " + address + '\'' +
                ", Location = " + location + '\'' +
                ", Price = " + price + '\'' +
                ", Description = " + description + '\'' +
                ", Photos = " + Arrays.toString(photos_ids) + '\'' +
                ", Review = " + Arrays.toString(review_ids) + '\'' +
                '}';
    }

}
