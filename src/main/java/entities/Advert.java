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

    public Advert(int id, String address, String location, int price, String description){
        setId(id);
        setAddress(address);
        setLocation(location);
        setPrice(price);
        setDescription(description);
    }


    public void addPhoto (int photos_id){
        photos_ids.add(photos_id);
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
