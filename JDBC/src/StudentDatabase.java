import java.sql.Connection;
import java.sql.DriverManager;

public class StudentDatabase {

    public static void main(String[] args) {

        try {
            // Connect to Student database
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentdb",
                    "root",
                    "Bhoomi@2006"
            );

            // Check connection status
            if (con != null) {
                System.out.println(
                    "Student database connected successfully."
                );
            }

            // Close connection
            con.close();

        } catch (Exception e) {
            System.out.println("Student database connection failed.");
            System.out.println(e.getMessage());
        }
    }
}