package repositories;

import data.interfaces.IDB;
import entities.Advert;
import entities.Review;
import entities.User;
import repositories.interfaces.IUserRepository;


import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createUser(User user) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO users(name, surname, gender, date_of_birth) VALUES (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setBoolean(3, user.getGender());
            st.setDate(4, user.getDate_of_birth());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("sql error: " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public User getUser(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,gender,date_of_birth FROM users WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"),
                        rs.getDate("date_of_birth"));
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

    @Override
    public List<User> getAllUsers() {

        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,gender,date_of_birth,owned_adverts_ids FROM users";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new LinkedList<>();
            while (rs.next()) {
                Array a = rs.getArray("owned_adverts_ids");
                System.out.println(a);
                System.out.println("this is for user with id:" + rs.getInt("id"));
                if (a != null){
                    Object[] array = (Object[]) a.getArray();
                    Integer[] intArray = Arrays.copyOf(array, array.length, Integer[].class);

                    User user = new User(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getBoolean("gender"),
                            rs.getDate("date_of_birth"),
                            intArray);
                    users.add(user);
                }
                else{
                    User user = new User(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getBoolean("gender"),
                            rs.getDate("date_of_birth"));
                    users.add(user);
                }
            }

            return users;
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

    @Override
    public List<Advert> getOwnedAdverts(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * FROM adverts WHERE id IN (\n" +
                    "\tSELECT unnest(owned_adverts_ids) \n" +
                    "    FROM users\n" +
                    "    WHERE id = ?\n" +
                    ")";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,id);

            ResultSet rs = st.executeQuery();
            List<Advert> adverts = new LinkedList<>();
            while (rs.next()) {

                Advert advert = new Advert(rs.getInt("id"),
                        rs.getString("address"),
                        rs.getString("price"),
                        rs.getString("description"),
                        rs.getArray("photos_ids"));

                adverts.add(advert);
            }

            return adverts;
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

    @Override
    public List<Review> getMadeReviews() {
        return null;
    }
}

