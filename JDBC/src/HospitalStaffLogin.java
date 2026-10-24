import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class HospitalStaffLogin {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Bhoomi@2006";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Hospital Staff Portal ===");
        System.out.print("Enter Staff ID: ");
        String staffId = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Parameterized query for security and precompilation
        String query = "SELECT role FROM hospital_staff WHERE staff_id = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, staffId);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    System.out.println("\n Authentication Successful!");
                    
                    // Display role-specific access messages
                    if ("Doctor".equalsIgnoreCase(role)) {
                        System.out.println("Access Granted: Welcome, Dr. " + staffId + ". Redirecting to Doctor Dashboard (Patient Records & Prescriptions).");
                    } else if ("Nurse".equalsIgnoreCase(role)) {
                        System.out.println("Access Granted: Welcome, Nurse " + staffId + ". Redirecting to Nursing Station (Vitals & Ward Management).");
                    } else {
                        System.out.println("Access Granted: Welcome, Staff member.");
                    }
                } else {
                    System.out.println("\n Authentication Failed: Invalid Staff ID or Password.");
                }
            }

        } catch (Exception e) {
            System.err.println("Database Error: " + e.getMessage());
        }
        scanner.close();
    }
}