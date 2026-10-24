import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SimpleLoginApp {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Bhoomi@2006";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== System Login ===");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // SQL Query with placeholders (?) to prevent SQL Injection
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set parameters
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\n Login Successful! Welcome, " + rs.getString("username") + ".");
                } else {
                    System.out.println("\n Invalid Username or Password. Access Denied.");
                }
            }

        } catch (Exception e) {
            System.err.println("Database Error: " + e.getMessage());
        }
        scanner.close();
    }
}