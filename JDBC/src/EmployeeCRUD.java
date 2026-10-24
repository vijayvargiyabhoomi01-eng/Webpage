import java.sql.*;
import java.util.Scanner;

public class EmployeeCRUD {

    static final String URL = "jdbc:mysql://localhost:3306/company";
    static final String USER = "root";
    static final String PASSWORD = "Bhoomi@2006";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            // Establish connection
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            int choice;

            do {
                System.out.println("\n--- Employee Management ---");
                System.out.println("1. Insert Employee");
                System.out.println("2. Display Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {

                    // CREATE
                    case 1:
                        System.out.print("Enter Employee ID: ");
                        int id = sc.nextInt();

                        System.out.print("Enter Employee Name: ");
                        String name = sc.next();

                        System.out.print("Enter Salary: ");
                        double salary = sc.nextDouble();

                        String insertQuery =
                                "INSERT INTO employee VALUES (?, ?, ?)";

                        PreparedStatement insert =
                                con.prepareStatement(insertQuery);

                        insert.setInt(1, id);
                        insert.setString(2, name);
                        insert.setDouble(3, salary);

                        insert.executeUpdate();
                        System.out.println("Employee inserted successfully.");
                        break;

                    // READ
                    case 2:
                        String selectQuery = "SELECT * FROM employee";

                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(selectQuery);

                        System.out.println("\nID\tName\tSalary");

                        while (rs.next()) {
                            System.out.println(
                                    rs.getInt("id") + "\t" +
                                    rs.getString("name") + "\t" +
                                    rs.getDouble("salary")
                            );
                        }
                        break;

                    // UPDATE
                    case 3:
                        System.out.print("Enter Employee ID to update: ");
                        int updateId = sc.nextInt();

                        System.out.print("Enter new salary: ");
                        double newSalary = sc.nextDouble();

                        String updateQuery =
                                "UPDATE employee SET salary = ? WHERE id = ?";

                        PreparedStatement update =
                                con.prepareStatement(updateQuery);

                        update.setDouble(1, newSalary);
                        update.setInt(2, updateId);

                        update.executeUpdate();
                        System.out.println("Employee updated successfully.");
                        break;

                    // DELETE
                    case 4:
                        System.out.print("Enter Employee ID to delete: ");
                        int deleteId = sc.nextInt();

                        String deleteQuery =
                                "DELETE FROM employee WHERE id = ?";

                        PreparedStatement delete =
                                con.prepareStatement(deleteQuery);

                        delete.setInt(1, deleteId);

                        delete.executeUpdate();
                        System.out.println("Employee deleted successfully.");
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