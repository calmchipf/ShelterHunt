package entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Advert {
    private int id;
    private String address;
    private String location;
    private int price;
    private String description;
    private ArrayList<Integer> photos_ids = new ArrayList<>();
    private ArrayList<Integer> review_ids = new ArrayList<>();

    // Constructor to initialize an advert with basic information
    public Advert(String address, int price, String description){
        setAddress(address);
        setPrice(price);
        setDescription(description);
    }

    // Method to add a photo ID related to the advert
    public void addPhoto (int photos_id){
        photos_ids.add(photos_id);
    }

    // Method to add a review ID related to the advert
    public void addReview_id(int review_id){
        review_ids.add(review_id);
    }

    // Method to generate a string representation of the advert
    @Override
    public String toString() {
        return "[ID : " + id + "]" + "Address: " + address + " | Price: " + price +
                " tenge | Desc: " + description + " ||| photos: " + photos_ids + " | reviews: " + review_ids;
    }
}
