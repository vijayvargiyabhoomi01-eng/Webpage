import java.sql.*;

public class ProductSelect {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/productdb";
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
            String query = "SELECT * FROM product";

            // Execute query
            ResultSet rs = stmt.executeQuery(query);

            // Display product details
            System.out.println("\nProduct ID\tProduct Name\tQuantity\tPrice");
            System.out.println("------------------------------------------------------");

            while (rs.next()) {

                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                System.out.println(productId + "\t\t" +
                                   productName + "\t\t" +
                                   quantity + "\t\t" +
                                   price);
            }

            // Close connection
            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}