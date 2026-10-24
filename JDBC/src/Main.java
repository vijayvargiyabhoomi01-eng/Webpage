import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library",
                "root",
                "Bhoomi@2006"
            );

            String sql = "INSERT INTO author (Aid, Aname, Email, Phoneno) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, 8);
            ps.setString(2, "Bhoomi");
            ps.setString(3, "bhoomi@gmail.com");
            ps.setString(4, "9876543210");

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Record inserted successfully!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}