import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class BookIssueTracking extends JFrame {

    JTextField bookIdField, studentField, issueDateField, returnDateField;
    JButton addButton, viewButton, updateButton, deleteButton;

    Connection con;

    BookIssueTracking() {

        setTitle("Book Issue Tracking System");
        setSize(500, 350);
        setLayout(new GridLayout(6, 2, 10, 10));

        JLabel bookIdLabel = new JLabel("Book ID:");
        JLabel studentLabel = new JLabel("Student Name:");
        JLabel issueLabel = new JLabel("Issue Date (YYYY-MM-DD):");
        JLabel returnLabel = new JLabel("Return Date (YYYY-MM-DD):");

        bookIdField = new JTextField();
        studentField = new JTextField();
        issueDateField = new JTextField();
        returnDateField = new JTextField();

        addButton = new JButton("Add");
        viewButton = new JButton("View");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        add(bookIdLabel);
        add(bookIdField);

        add(studentLabel);
        add(studentField);

        add(issueLabel);
        add(issueDateField);

        add(returnLabel);
        add(returnDateField);

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

            System.out.println("Database Connected!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Database Connection Failed: " + e.getMessage());
        }

        addButton.addActionListener(e -> addRecord());
        viewButton.addActionListener(e -> viewRecords());
        updateButton.addActionListener(e -> updateRecord());
        deleteButton.addActionListener(e -> deleteRecord());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void addRecord() {

        try {
            String query =
                    "INSERT INTO book_issue VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(bookIdField.getText()));
            ps.setString(2, studentField.getText());
            ps.setDate(3, Date.valueOf(issueDateField.getText()));
            ps.setDate(4, Date.valueOf(returnDateField.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Book Issue Record Added Successfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage());
        }
    }

    void viewRecords() {

        try {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM book_issue");

            String data = "";

            while (rs.next()) {

                data += "Book ID: " + rs.getInt("book_id")
                        + "\nStudent: " + rs.getString("student_name")
                        + "\nIssue Date: " + rs.getDate("issue_date")
                        + "\nReturn Date: " + rs.getDate("return_date")
                        + "\n--------------------------\n";
            }

            JOptionPane.showMessageDialog(this, data);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage());
        }
    }

    void updateRecord() {

        try {
            String query =
                    "UPDATE book_issue SET student_name=?, issue_date=?, return_date=? WHERE book_id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, studentField.getText());
            ps.setDate(2, Date.valueOf(issueDateField.getText()));
            ps.setDate(3, Date.valueOf(returnDateField.getText()));
            ps.setInt(4, Integer.parseInt(bookIdField.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Record Updated Successfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage());
        }
    }

    void deleteRecord() {

        try {
            String query =
                    "DELETE FROM book_issue WHERE book_id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(bookIdField.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Record Deleted Successfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new BookIssueTracking();
    }
}