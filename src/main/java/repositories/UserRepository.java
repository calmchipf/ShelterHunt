package repositories;

import data.interfaces.IDB;
import entities.Advert;
import entities.Review;
import entities.User;
import repositories.interfaces.IUserRepository;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    // Method to retrieve a user from the database by ID
    @Override
    public User getUser(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * FROM users WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Array a = rs.getArray("owned_adverts_ids");
                Array b = rs.getArray("fav_adverts_ids");
                Array c = rs.getArray("fav_adverts_ids");


                return new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"),
                        rs.getDate("date_of_birth"),
                        rs.getLong("phone_number"),
                        turnToArrList(a), turnToArrList(b), turnToArrList(c),
                        rs.getString("username"),
                        rs.getString("password"));
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

    // Method to get the last user by getting the one with max id
    @Override
    public User getLastUser() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * FROM users WHERE id = (SELECT MAX(id) FROM users)";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();


            if (rs.next()) {
                Array a = rs.getArray("owned_adverts_ids");
                Array b = rs.getArray("fav_adverts_ids");
                Array c = rs.getArray("fav_adverts_ids");


                return new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"),
                        rs.getDate("date_of_birth"),
                        rs.getLong("phone_number"),
                        turnToArrList(a), turnToArrList(b), turnToArrList(c),
                        rs.getString("username"),
                        rs.getString("password"));
            }
            else{
                return null;
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
    public void updateUser(int id, String column_name, String new_info){

        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "update users set " + column_name + " = ? where id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            if (column_name == "gender") {
                st.setBoolean(1, new_info.equalsIgnoreCase("male"));
            }
            else if (column_name == "date_of_birth") {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                st.setDate(1, new Date(dateFormat.parse(new_info).getTime()));
            } else if (column_name == "phone_number") {
                st.setLong(1, Long.parseLong(new_info));
            }
            else {
                st.setString(1, new_info);
            }
            st.setInt(2, id);

            st.executeUpdate();
            System.out.println("Hey, you changed your " + column_name + " to " + new_info + " !");

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
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


                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"),
                        rs.getDate("date_of_birth"),
                        rs.getLong("phone_number"),
                        turnToArrList(a), turnToArrList(b), turnToArrList(c),
                        rs.getString("username"),
                        rs.getString("password"));
                users.add(user);
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
            String sql = "SELECT * FROM adverts WHERE id IN (SELECT DISTINCT unnest(owned_adverts_ids) FROM users WHERE id = ?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,id);

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

    @Override
    public void deleteUser(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,id);

            st.executeUpdate();
            System.out.println("No more user with id: " + id + "... :_( Pomyanem");

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
    }
}

