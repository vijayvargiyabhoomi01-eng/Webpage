import java.sql.*;
import java.util.Scanner;

public class StudentCRUD {

    static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "root";
    static final String PASSWORD = "Bhoomi@2006";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            // Establish database connection
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            int choice;

            do {
                System.out.println("\n--- Student Management System ---");
                System.out.println("1. Create Student");
                System.out.println("2. Read Student Records");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();

                switch (choice) {

                    // CREATE
                    case 1:
                        System.out.print("Enter Roll Number: ");
                        int roll = sc.nextInt();

                        System.out.print("Enter Name: ");
                        String name = sc.next();

                        System.out.print("Enter Course: ");
                        String course = sc.next();

                        System.out.print("Enter Marks: ");
                        double marks = sc.nextDouble();

                        String insertQuery =
                                "INSERT INTO student VALUES (?, ?, ?, ?)";

                        PreparedStatement insert =
                                con.prepareStatement(insertQuery);

                        insert.setInt(1, roll);
                        insert.setString(2, name);
                        insert.setString(3, course);
                        insert.setDouble(4, marks);

                        insert.executeUpdate();

                        System.out.println("Student record inserted successfully.");
                        break;

                    // READ
                    case 2:
                        String selectQuery = "SELECT * FROM student";

                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(selectQuery);

                        System.out.println("\nRoll\tName\tCourse\tMarks");

                        while (rs.next()) {
                            System.out.println(
                                    rs.getInt("roll") + "\t" +
                                    rs.getString("name") + "\t" +
                                    rs.getString("course") + "\t" +
                                    rs.getDouble("marks")
                            );
                        }
                        break;

                    // UPDATE
                    case 3:
                        System.out.print("Enter Roll Number to update: ");
                        int updateRoll = sc.nextInt();

                        System.out.print("Enter new marks: ");
                        double newMarks = sc.nextDouble();

                        String updateQuery =
                                "UPDATE student SET marks = ? WHERE roll = ?";

                        PreparedStatement update =
                                con.prepareStatement(updateQuery);

                        update.setDouble(1, newMarks);
                        update.setInt(2, updateRoll);

                        update.executeUpdate();

                        System.out.println("Student record updated successfully.");
                        break;

                    // DELETE
                    case 4:
                        System.out.print("Enter Roll Number to delete: ");
                        int deleteRoll = sc.nextInt();

                        String deleteQuery =
                                "DELETE FROM student WHERE roll = ?";

                        PreparedStatement delete =
                                con.prepareStatement(deleteQuery);

                        delete.setInt(1, deleteRoll);

                        delete.executeUpdate();

                        System.out.println("Student record deleted successfully.");
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } while (choice != 5);

            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}