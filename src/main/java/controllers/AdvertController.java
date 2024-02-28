package controllers;

import entities.Advert;
import entities.User;
import repositories.interfaces.IAdvertRepository;
import repositories.interfaces.IUserRepository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class AdvertController {
    private final IAdvertRepository repo;
    private int currentIndex;

    // Constructor to initialize AdvertController with an advert repository
    public AdvertController(IAdvertRepository repo) {
        this.repo = repo;
        this.currentIndex = 0;
    }

    public boolean addAdvert(Advert advert, int userId){
        boolean advert_added = repo.addAdvert(advert, userId);

        return advert_added;
    }

    // Method to retrieve the next advert
    public Advert getNextAdvert() {
        List<Advert> adverts = repo.getAllAdverts();

        if (adverts.isEmpty()) {
            return null;
        }

        Advert nextAdvert = adverts.get(currentIndex);

        currentIndex++;

        if (currentIndex >= adverts.size()) {
            currentIndex = 0;
        }

        return nextAdvert;
    }
}
