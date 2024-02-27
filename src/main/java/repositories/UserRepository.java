package repositories;

import data.interfaces.IDB;
import entities.Advert;
import entities.Review;
import entities.User;
import repositories.interfaces.IUserRepository;


import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;

    // Constructor to initialize UserRepository with a database instance
    public UserRepository(IDB db) {
        this.db = db;
    }

    // Returns true if the user creation is successful, false otherwise
    @Override
    public boolean createUser(User user) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO users(username,password,name, surname, gender, date_of_birth, phone_number, owned_adverts_ids, fav_adverts_ids, reviews_ids) VALUES (? ,? ,?, ?, ?, ?, 0, '{}', '{}', '{}')";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1,user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getName());
            st.setString(4, user.getSurname());
            st.setBoolean(5, user.getGender());
            st.setDate(6, user.getDate_of_birth());

            st.execute();

            System.out.println("SUCCESSFULLY CREATED A USER!");

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

    // Method to retrieve a user from the database by ID
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

    // Method to retrieve a user from the database by username and password
    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,gender,date_of_birth,phone_number,owned_adverts_ids,fav_adverts_ids,reviews_ids,username,password FROM users WHERE username = ? and password = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();


            if (rs.next()) {

                Array a = rs.getArray("owned_adverts_ids");
                Array b = rs.getArray("fav_adverts_ids");
                Array c = rs.getArray("fav_adverts_ids");
                if (a != null && b != null && c != null) {
                    Object[] arraya = (Object[]) a.getArray();
                    Object[] arrayb = (Object[]) b.getArray();
                    Object[] arrayc = (Object[]) c.getArray();
                    ArrayList<Integer> owned_adverts = new ArrayList<>();
                    ArrayList<Integer> fav_adverts = new ArrayList<>();
                    ArrayList<Integer> reviews = new ArrayList<>();
                    for (Object obj : arraya) {
                        owned_adverts.add((Integer) obj);
                    }
                    for (Object obj : arrayb) {
                        owned_adverts.add((Integer) obj);
                    }
                    for (Object obj : arrayc) {
                        owned_adverts.add((Integer) obj);
                    }

                    User user = new User(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getBoolean("gender"),
                            rs.getDate("date_of_birth"),
                            rs.getLong("phone_number"),
                            owned_adverts, fav_adverts, reviews, rs.getString("username"),
                            rs.getString("password"));

                    return user;
                }else{
                    return null;
                }
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

    // Method to retrieve all users from the database
    @Override
    public ArrayList<User> getAllUsers() {

        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * FROM users";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            ArrayList<User> users = new ArrayList<>();
            while (rs.next()) {
                Array a = rs.getArray("owned_adverts_ids");
                Array b = rs.getArray("fav_adverts_ids");
                Array c = rs.getArray("fav_adverts_ids");
                if (a != null && b != null && c != null){
                    Object[] arraya = (Object[]) a.getArray();
                    Object[] arrayb = (Object[]) b.getArray();
                    Object[] arrayc = (Object[]) c.getArray();
                    ArrayList<Integer> owned_adverts = new ArrayList<>();
                    ArrayList<Integer> fav_adverts = new ArrayList<>();
                    ArrayList<Integer> reviews = new ArrayList<>();
                    for (Object obj : arraya) {
                        owned_adverts.add((Integer) obj);
                    }
                    for (Object obj : arrayb) {
                        owned_adverts.add((Integer) obj);
                    }
                    for (Object obj : arrayc) {
                        owned_adverts.add((Integer) obj);
                    }

                    User user = new User(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getBoolean("gender"),
                            rs.getDate("date_of_birth"),
                            rs.getLong("phone_number"),
                            owned_adverts, fav_adverts, reviews, rs.getString("username"),
                            rs.getString("password"));
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
                    "\tSELECT DISTINCT unnest(owned_adverts_ids) \n" +
                    "    FROM users\n" +
                    "    WHERE id = ?\n" +
                    ")";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,id);

            ResultSet rs = st.executeQuery();
            List<Advert> adverts = new LinkedList<>();
            while (rs.next()) {

                Array a = rs.getArray("photos_ids");
                Array b = rs.getArray("reviews_ids");

                if (a != null) {
                    Object[] array_a = (Object[]) a.getArray();
                    Object[] array_b = (Object[]) b.getArray();
                    ArrayList<Integer> list_a = new ArrayList<>();
                    ArrayList<Integer> list_b = new ArrayList<>();
                    for (Object obj : array_a) {list_a.add((Integer) obj);}
                    for (Object obj : array_b) {list_b.add((Integer) obj);}
                    Advert advert = new Advert(rs.getInt("id"),
                            rs.getString("address"),
                            rs.getString("location"),
                            rs.getInt("price"),
                            rs.getString(   "description"),
                            list_a,list_b);

                    adverts.add(advert);
                }
                else {
                    Advert advert = new Advert(rs.getInt("id"),
                            rs.getString("address"),
                            rs.getString("location"),
                            rs.getInt("price"),
                            rs.getString("description"));

                    adverts.add(advert);
                }


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

