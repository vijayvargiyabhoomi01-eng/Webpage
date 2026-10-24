import java.sql.*;

public class StudentSelect {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/studentdb";
        String user = "root";
        String password = "Bhoomi@2006";

        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            Connection con = DriverManager.getConnection(url, user, password);

            System.out.println("Database Connected Successfully!");

            // Create Statement
            Statement stmt = con.createStatement();

            // SELECT query
            String query = "SELECT * FROM student";

            // Execute query
            ResultSet rs = stmt.executeQuery(query);

            // Display student records
            System.out.println("\nID\tName\tMarks");
            System.out.println("---------------------------");

            while (rs.next()) {
                int RollNo = rs.getInt("RollNo");
                String name = rs.getString("Name");
                String Course_name = rs.getString("Course_Name");
                int marks = rs.getInt("marks");
                int fees = rs.getInt("fees");

                System.out.println(RollNo + "\t" + name + "\t" + Course_name + "\t" + marks + "\t" + fees);

            }

            // Close connection
            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}