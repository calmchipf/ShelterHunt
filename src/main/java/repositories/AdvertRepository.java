package repositories;

import data.interfaces.IDB;
import entities.Advert;
import entities.Review;
import repositories.interfaces.IAdvertRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdvertRepository implements IAdvertRepository {
    private final IDB db;

    public AdvertRepository(IDB db) {
        this.db = db;
    }

    @Override
    public List<Review> checkReviews(int id) {
        return null;
    }

    @Override
    public List<Advert> getAllAdverts() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM adverts";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<Advert> adverts = new LinkedList<>();
            while (rs.next()) {
                Array a = rs.getArray("photos_ids");
                Array b = rs.getArray("reviews_ids");
                if (a == null) {
                    Advert advert = new Advert(rs.getInt("id"), rs.getString("address"), rs.getString("location"), rs.getInt("price"), rs.getString("description"));
                    adverts.add(advert);
                } else {
                    Object[] array_a = (Object[]) a.getArray();
                    Object[] array_b = (Object[]) b.getArray();
                    ArrayList<Integer> list_a = new ArrayList<>();
                    ArrayList<Integer> list_b = new ArrayList<>();
                    for (Object obj : array_a) {
                        list_a.add((Integer) obj);
                    }
                    for (Object obj : array_b) {
                        list_b.add((Integer) obj);
                    }
                    Advert advert = new Advert(rs.getInt("id"), rs.getString("address"), rs.getString("location"), rs.getInt("price"), rs.getString("description"), list_a, list_b);
                    adverts.add(advert);
                }
            }
            return adverts;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    // Другие методы для работы с объявлениями
}