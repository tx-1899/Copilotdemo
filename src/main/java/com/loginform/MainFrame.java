package com.loginform;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame{
    // Create the initiailize method that requires a user object
    public void initialize(User user) {
        /************** Info Panel ********/
        // Create a JPanel called infoPanel and instantiate it with a new JPanel. Set the layout of infoPanel to new GridLayout(0, 2, 5, 5)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 2, 5, 5));

        // display the user information in the infoPanel
        infoPanel.add(new JLabel("ID:"));
        infoPanel.add(new JLabel(String.valueOf(user.id)));
        infoPanel.add(new JLabel("Email:"));
        infoPanel.add(new JLabel(user.email));
        infoPanel.add(new JLabel("Phone:"));
        infoPanel.add(new JLabel(user.phone));
        infoPanel.add(new JLabel("Address:"));

        // Create a StringBuilder called address and instantiate it with a new StringBuilder. Append the address, city, state, and zip of the user to the address
        StringBuilder address = new StringBuilder();
        address.append(user.address).append(", ").append(user.city).append(", ").append(user.state).append(" ").append(user.zip);
        infoPanel.add(new JLabel(address.toString()));
        
        // add the info panel to the north of the frame
        add(infoPanel, BorderLayout.NORTH);
        


        // Set title  to 'Dashboard, default close operation to dispose on close, set size to 1100x650,set location relative to null, and set visible to true'
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
