import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HospitalUI extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Bhoomi@2006";

    public HospitalUI() {
        setTitle("Hospital Portal System");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("General Login", createGeneralLoginPanel());
        tabbedPane.addTab("Staff Login", createStaffLoginPanel());

        add(tabbedPane);
    }

    private JPanel createGeneralLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("=== System Login ===", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel lblUser = new JLabel("Username:");
        JTextField txtUser = new JTextField(15);
        JLabel lblPass = new JLabel("Password:");
        JPasswordField txtPass = new JPasswordField(15);
        JButton btnLogin = new JButton("Login");
        JLabel lblStatus = new JLabel("", SwingConstants.CENTER);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblUser, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtUser, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblPass, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(txtPass, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(btnLogin, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(lblStatus, gbc);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUser.getText();
                String password = new String(txtPass.getPassword());

                String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(query)) {

                    pstmt.setString(1, username);
                    pstmt.setString(2, password);

                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            lblStatus.setForeground(Color.GREEN.darker());
                            lblStatus.setText("Login Successful! Welcome, " + rs.getString("username") + ".");
                        } else {
                            lblStatus.setForeground(Color.RED);
                            lblStatus.setText("Invalid Username or Password.");
                        }
                    }
                } catch (Exception ex) {
                    lblStatus.setForeground(Color.RED);
                    lblStatus.setText("Database Error: " + ex.getMessage());
                }
            }
        });

        return panel;
    }

    private JPanel createStaffLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("=== Hospital Staff Portal ===", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel lblStaffId = new JLabel("Staff ID:");
        JTextField txtStaffId = new JTextField(15);
        JLabel lblPass = new JLabel("Password:");
        JPasswordField txtPass = new JPasswordField(15);
        JButton btnLogin = new JButton("Login");
        JLabel lblStatus = new JLabel("", SwingConstants.CENTER);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblStaffId, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtStaffId, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblPass, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(txtPass, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(btnLogin, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(lblStatus, gbc);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String staffId = txtStaffId.getText();
                String password = new String(txtPass.getPassword());

                String query = "SELECT role FROM hospital_staff WHERE staff_id = ? AND password = ?";
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(query)) {

                    pstmt.setString(1, staffId);
                    pstmt.setString(2, password);

                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            String role = rs.getString("role");
                            lblStatus.setForeground(Color.GREEN.darker());
                            
                            if ("Doctor".equalsIgnoreCase(role)) {
                                lblStatus.setText("Access Granted: Welcome, Dr. " + staffId);
                            } else if ("Nurse".equalsIgnoreCase(role)) {
                                lblStatus.setText("Access Granted: Welcome, Nurse " + staffId);
                            } else {
                                lblStatus.setText("Access Granted: Welcome, Staff member.");
                            }
                        } else {
                            lblStatus.setForeground(Color.RED);
                            lblStatus.setText("Authentication Failed: Invalid Staff ID or Password.");
                        }
                    }
                } catch (Exception ex) {
                    lblStatus.setForeground(Color.RED);
                    lblStatus.setText("Database Error: " + ex.getMessage());
                }
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HospitalUI().setVisible(true);
        });
    }
}