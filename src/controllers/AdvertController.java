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

    public AdvertController(IAdvertRepository repo) {
        this.repo = repo;
    }
}
