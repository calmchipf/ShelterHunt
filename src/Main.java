import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:postgresql://localhost:5432/ShelterHunt";
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection(connectionUrl, "postgres", "ugotitright");

            stmt = con.createStatement();

            rs = stmt.executeQuery("select * from users");

            while (rs.next())
                System.out.println(rs.getInt("ID") + "  " + rs.getString("Name") + "  " + rs.getString("Surname"));
        }
        catch (Exception e) {
            System.out.println("Exception occurred!");
        } finally {

            try { // Close connection - clean up the system resources
                con.close();
            } catch (Exception e) {
                System.out.println("Exception occurred!");
            }
        }

        System.out.println("Finished!");


    }
}