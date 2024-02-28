package repositories;

import data.interfaces.IDB;
import entities.Advert;
import entities.Review;
import entities.User;
import repositories.interfaces.IAdvertRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdvertRepository implements IAdvertRepository {
    private final IDB db;

    // Constructor to initialize AdvertRepository with a database connection
    public AdvertRepository(IDB db) {
        this.db = db;
    }

    // Method to add a new advert to the database and associate it with a user
    @Override
    public boolean addAdvert(Advert advert, int userId) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO adverts(address, price, description) VALUES (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, advert.getAddress());
            st.setInt(2, advert.getPrice());
            st.setString(3, advert.getDescription());

            st.executeUpdate();

            int advertId = getIdOfAdvertByEverythingElse(advert.getAddress(), advert.getPrice(), advert.getDescription());

            sql = "UPDATE users SET owned_adverts_ids = array_append(owned_adverts_ids, ?) WHERE id = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, advertId);
            st.setInt(2, userId);
            st.executeUpdate();

            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    // Method to check reviews associated with an advert
    @Override
    public List<Review> checkReviews(int id) {
        return null;
    }

    // Method to retrieve all adverts from the database
    @Override
    public List<Advert> getAllAdverts() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM adverts ORDER BY id";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<Advert> adverts = new LinkedList<>();
            while (rs.next()) {
                Array a = rs.getArray("photos_ids");
                Array b = rs.getArray("reviews_ids");
                if (a == null) {
                    Advert advert = new Advert(rs.getInt("id"), rs.getString("address"), rs.getInt("price"), rs.getString("description"));
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
                    Advert advert = new Advert(rs.getInt("id"), rs.getString("address"), rs.getInt("price"), rs.getString("description"), list_a, list_b);
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

    // A very bad practice. But I have no idea how to make implement it otherwise, which makes me incredibly sad.
    public int getIdOfAdvertByEverythingElse(String address, int price, String description){
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id FROM adverts WHERE address = ? and price = ? and description = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, address);
            st.setInt(2, price);
            st.setString(3, description);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }

        return 0;
    }

}