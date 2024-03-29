package com.loginform;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class LoginForm extends JFrame
{
    // create a font called mainFont that is 'Segoe print', Bold, and size 18 make it final and private
    private final Font mainFont = new Font("Segoe Print", Font.BOLD, 18);

    // create JTextField called tfemail, and a JPasswordField called pfpassword make them private
    private JTextField tfemail;
    private JPasswordField pfpassword;

    // create a method initailize that sets the title to 'Login Form', disposes on close, sets size to 400x500, minimum size to 350x450, sets location relative to null and sets visible to true
    private void initialize() {

        /******************Form Panel ******************/
        //Create a JLabel lbLoginForm with text 'Login Form' and SwingConstants.CENTER
        JLabel lbLoginForm = new JLabel("Login Form", SwingConstants.CENTER);

        //Set the font of lbLoginForm to mainFont
        lbLoginForm.setFont(mainFont);

        // create new JTextField tfemail, and new JPasswordField pfpassword and set the font of each to mainFont
        tfemail = new JTextField();
        tfemail.setFont(mainFont);
        pfpassword = new JPasswordField();
        pfpassword.setFont(mainFont);

        //Add Labels for email and password
        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(mainFont);        

        // create a JPanel called formPanel and instantiate it with a new JPanel. Set the layout of formPanel to new GridLayout(0, 1, 10, 10)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 10, 10));

        // add lbLoginForm, lbEmail, and tfemail to formPanel. Then add lbPassword and pfpassword to formPanel
        formPanel.add(lbLoginForm);
        formPanel.add(lbEmail);
        formPanel.add(tfemail);
        formPanel.add(new JLabel("Password"));
        formPanel.add(pfpassword);

        /************* Add Button Panel **************/
        //Create a JButton called btnLogin with text 'Login'. Set the font of btnLogin to mainFont. Create an ActionListener for btnLogin
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(mainFont);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Create a String called email and set it to the text of tfemail
                String email = tfemail.getText();
                //Create a String called password and set it to the text of pfpassword
                String password = new String(pfpassword.getPassword());

                // create a new User object called user and set the email and password using a method called getAuthenticatedUser
                User user = getAuthenticatedUser(email, password);

                // if the user is not null, create a MainFrame object called mainFrame and dispose of the current frame. Initialize the mainFrame with user
                if (user != null) {
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.initialize(user);
                    dispose();
                } else {
                    // if the user is null, on the LoginForm frame, show a message dialog with the message 'Invalid email or password. Please try again.'
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid email or password. Please try again.");                
                }
            }            
        });

        // Create a JButton called btnCancel with text 'Cancel'. Set the font of btnCancel to mainFont. Create an ActionListener for btnCancel
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(mainFont);

        // add an action listener to btnCancel that disposes of the current frame
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // on the LoginForm frame, dispose of the frame
                dispose();
            }
        });

        // create a JPanel called buttonPanel and instantiate it with a new JPanel. Set the layout of buttonPanel to new GridLayout(1,2,10,0)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));


        // add buttons btnLogin and btnCancel to buttonsPanel
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnCancel);

        /*************Initialize the frame *************/

        // add the panel to the JFrame on the north side
        add(formPanel, BorderLayout.NORTH);

        // add the buttonsPanel to the JFrame on the south side
        add(buttonPanel, BorderLayout.SOUTH);


        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setMinimumSize(new Dimension(350, 450));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // create a method called getAuthenticatedUser that takes in a String email and a String password
    private User getAuthenticatedUser(String email, String password) {
        // create a null user object
        User user = null;

        // Create final strings for DB_URL with JDBC Connector connecting to Employee databawse, USERNAME, and PASSWORD
        final String DB_URL = "jdbc:mysql://localhost/Employee";
        // create final String for USERNAME with value of 'root'
        final String USERNAME = "root";
        // create final String for PASSWORD with value of ''
        final String PASSWORD = "";

        // create a try-catch block
        try {
            // create a Connection called conn and set it to null
            Connection conn = null;

            // create a Statement called stmt and set it to null
            Statement stmt = null;

            // create a ResultSet called rs and set it to null
            ResultSet rs = null;

            // create a String called query and set it to "SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "'"
            String query = "SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "'";

            // create a Class.forName for com.mysql.jdbc.Driver
            Class.forName("com.mysql.jdbc.Driver");

            // create a connection to the database using DriverManager.getConnection with DB_URL, USERNAME, and PASSWORD
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            // create a statement using conn.createStatement
            stmt = conn.createStatement();

            // create a ResultSet called rs and set it to stmt.executeQuery(query)
            rs = stmt.executeQuery(query);

            // if rs.next() is true, create a new User object called user and set the id, email, password, phone, address, city, state, and zip from the result set
            if (rs.next()) {
                user = new User();
                user.id = rs.getInt("id");
                user.email = rs.getString("email");
                user.password = rs.getString("password");
                user.phone = rs.getString("phone");
                user.address = rs.getString("address");
                user.city = rs.getString("city");
                user.state = rs.getString("state");
                user.zip = rs.getString("zip");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return user;
            
    }
    
    public static void main( String[] args )
    {
        // Create a new LoginForm object called loginForm and initialize it
        LoginForm loginForm = new LoginForm();
        loginForm.initialize();
    }
}
