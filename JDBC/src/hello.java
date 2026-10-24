import java.sql.Connection;
import java.sql.DriverManager;

public class hello {

    public static void main(String[] args) {

        try {
            // Establish connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb",
                    "root",
                    "Bhoomi@2006"
            );

            // Display connection status
            if (con != null) {
                System.out.println("Database connected successfully!");
            }

            // Close connection
            con.close();

        } catch (Exception e) {
            System.out.println("Database connection failed!");
            System.out.println(e);
        }
    }
}