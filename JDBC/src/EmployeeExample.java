import java.sql.*;

public class EmployeeExample {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/companydb";
        String user = "root";
        String password = "Bhoomi@2006";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, password);

            Statement stmt = con.createStatement();

            String query = "SELECT * FROM employee";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Employee Details:");
            System.out.println("ID\tName\tDepartment\tSalary");

            while (rs.next()) {

                int id = rs.getInt("emp_id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                double salary = rs.getDouble("salary");

                System.out.println(
                    id + "\t" + name + "\t" +
                    department + "\t\t" + salary
                );
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}