import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class LibraryManagement extends JFrame {

    JTextField idField, titleField, authorField, priceField;
    JButton addButton, viewButton, updateButton, deleteButton;

    Connection con;

    LibraryManagement() {

        setTitle("Library Management System");
        setSize(500, 350);
        setLayout(new GridLayout(6, 2, 10, 10));

        JLabel idLabel = new JLabel("Book ID:");
        JLabel titleLabel = new JLabel("Title:");
        JLabel authorLabel = new JLabel("Author:");
        JLabel priceLabel = new JLabel("Price:");

        idField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();
        priceField = new JTextField();

        addButton = new JButton("Add");
        viewButton = new JButton("View");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        add(idLabel);
        add(idField);

        add(titleLabel);
        add(titleField);

        add(authorLabel);
        add(authorField);

        add(priceLabel);
        add(priceField);

        add(addButton);
        add(viewButton);

        add(updateButton);
        add(deleteButton);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/librarydb",
                "root",
                "Bhoomi@2006"
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Database Connection Failed: " + e.getMessage());
        }

        addButton.addActionListener(e -> addBook());
        viewButton.addActionListener(e -> viewBooks());
        updateButton.addActionListener(e -> updateBook());
        deleteButton.addActionListener(e -> deleteBook());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void addBook() {

        try {
            String query =
                    "INSERT INTO books VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(idField.getText()));
            ps.setString(2, titleField.getText());
            ps.setString(3, authorField.getText());
            ps.setDouble(4, Double.parseDouble(priceField.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Book Added Successfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage());
        }
    }

    void viewBooks() {

        try {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM books");

            String data = "";

            while (rs.next()) {
                data += "ID: " + rs.getInt("book_id")
                        + "\nTitle: " + rs.getString("title")
                        + "\nAuthor: " + rs.getString("author")
                        + "\nPrice: " + rs.getDouble("price")
                        + "\n--------------------\n";
            }

            JOptionPane.showMessageDialog(this, data);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage());
        }
    }

    void updateBook() {

        try {
            String query =
                    "UPDATE books SET title=?, author=?, price=? WHERE book_id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, titleField.getText());
            ps.setString(2, authorField.getText());
            ps.setDouble(3, Double.parseDouble(priceField.getText()));
            ps.setInt(4, Integer.parseInt(idField.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Book Updated Successfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage());
        }
    }

    void deleteBook() {

        try {
            String query =
                    "DELETE FROM books WHERE book_id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(idField.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Book Deleted Successfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new LibraryManagement();
    }
}