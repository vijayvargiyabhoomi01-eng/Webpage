import java.sql.*;

public class SelectExample {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/st";
        String user = "root";
        String password = "Bhoomi@2006";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection(url, user, password);

            System.out.println("Database Connected Successfully!");

            // Create statement
            Statement stmt = con.createStatement();

            // Execute SELECT query
            String query = "SELECT * FROM student";
            ResultSet rs = stmt.executeQuery(query);

            // Display retrieved data
            System.out.println("\nStudent Details:");
            System.out.println("ID\tName\tMarks");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int marks = rs.getInt("marks");

                System.out.println(id + "\t" + name + "\t" + marks);
            }

            // Close connection
            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}