import java.sql.*;

public class JDBCExample {

    public static void main(String[] args) {

        try {
            // 1. DriverManager
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb",
                    "root",
                    "Bhoomi@2006"
            );

            System.out.println("Database connected successfully.");

            // 2. Connection
            Statement stmt = con.createStatement();

            // 3. Statement
            String query = "SELECT * FROM student";
            ResultSet rs = stmt.executeQuery(query);

            // Display records
            while (rs.next()) {
                System.out.println(
                        rs.getInt(1) + " " +
                        rs.getString(2)
                );
            }

            // Close connection
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}