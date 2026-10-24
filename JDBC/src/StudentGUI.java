import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class StudentGUI extends JFrame {

    JTextField idField, nameField, marksField;
    JButton insertButton, viewButton;

    Connection con;

    StudentGUI() {

        setTitle("Student Database");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        JLabel idLabel = new JLabel("ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel marksLabel = new JLabel("Marks:");

        idField = new JTextField();
        nameField = new JTextField();
        marksField = new JTextField();

        insertButton = new JButton("Insert");
        viewButton = new JButton("View");

        add(idLabel);
        add(idField);

        add(nameLabel);
        add(nameField);

        add(marksLabel);
        add(marksField);

        add(insertButton);
        add(viewButton);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentdb",
                "root",
                "Bhoomi@2006"
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Connection Failed");
        }

        insertButton.addActionListener(e -> insertData());
        viewButton.addActionListener(e -> viewData());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void insertData() {

        try {
            String query = "INSERT INTO student VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(idField.getText()));
            ps.setString(2, nameField.getText());
            ps.setInt(3, Integer.parseInt(marksField.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Record Inserted Successfully!");

            idField.setText("");
            nameField.setText("");
            marksField.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    void viewData() {

        try {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM student");

            StringBuilder data = new StringBuilder();

            while (rs.next()) {
                data.append("ID: ")
                    .append(rs.getInt("id"))
                    .append(", Name: ")
                    .append(rs.getString("name"))
                    .append(", Marks: ")
                    .append(rs.getInt("marks"))
                    .append("\n");
            }

            JOptionPane.showMessageDialog(this, data.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new StudentGUI();
    }
}