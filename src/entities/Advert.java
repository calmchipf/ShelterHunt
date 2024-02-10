package entities;

public class Adverts {
    public int id;
    public string address;
    public string location;
    public int price;
    public int photos_id;
    public string description;
    public int review_id;

    public Adverts(int id, string address, string location, int price, int photos_id, string description, int review_id){
        this.id = id;
        this.address = address;
        this.location = location;
        this.price = price;
        this.photos_id = photos_id;
        this.description = description;
        this.review_id = review_id;
    }
    // Getters and setters
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public string getAddress(){
        return address;
    }
    public void setAddress(string address){
        this.address = address;
    }
    public string getLocation(){
        return location;
    }
    public void setLocation(string location){
        this.location = location;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public int getPhotos_id(){
        return photos_id;
    }
    public void setPhotos_id(int photos_id){
        this.photos_id = photos_id;
    }
    public string getDescription(){
        return description;
    }
    public void setDescription(string description){
        this.description = description;
    }
    public int getReview_id(){
        return review_id;
    }
    public void setReview_id(int review_id){
        this.review_id = review_id;
    }
}
