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

            int advertId = getLastAdvert().getId();

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

    public ArrayList<Integer> turnToArrList(Array a) throws SQLException {
        if (a != null){
            Object[] arraya = (Object[]) a.getArray();
            ArrayList<Integer> array_list = new ArrayList<>();
            for (Object obj : arraya) {
                array_list.add((Integer) obj);
            }
            return array_list;
        }
        return new ArrayList<>();
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

                Advert advert = new Advert(rs.getInt("id"),
                        rs.getString("address"),
                        rs.getString("location"),
                        rs.getInt("price"),
                        rs.getString(   "description"),
                        turnToArrList(a),turnToArrList(b));

                adverts.add(advert);
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

    // Method to get the last advert by getting the one with max id
    public Advert getLastAdvert(){
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * FROM adverts WHERE id = (SELECT MAX(id) FROM adverts);\n";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();


            if (rs.next()) {
                Array a = rs.getArray("photos_ids");
                Array b = rs.getArray("reviews_ids");

                return new Advert(rs.getInt("id"),
                        rs.getString("address"),
                        rs.getString("location"),
                        rs.getInt("price"),
                        rs.getString(   "description"),
                        turnToArrList(a),turnToArrList(b));
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

        return null;
    }

}