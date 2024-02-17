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

    public AdvertController(IAdvertRepository repo) {
        this.repo = repo;
        this.currentIndex = 0;
    }

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
