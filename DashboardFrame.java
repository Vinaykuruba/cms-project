package cms.gui;

import javax.swing.*;

public class DashboardFrame extends JFrame {

    private final JButton btnAddContent;
    private final JButton btnViewContent;
    private final JButton btnLogout;
    private final JLabel lblTitle;

    public DashboardFrame() {
        setTitle("CMS Dashboard");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lblTitle = new JLabel("Welcome to the Content Management System");
        lblTitle.setBounds(160, 50, 400, 30);
        add(lblTitle);

        btnAddContent = new JButton("Add Content");
        btnAddContent.setBounds(250, 150, 180, 35);
        add(btnAddContent);

        btnViewContent = new JButton("View Content");
        btnViewContent.setBounds(250, 200, 180, 35);
        add(btnViewContent);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(250, 250, 180, 35);
        add(btnLogout);

        btnAddContent.addActionListener(e -> new AddContentFrame().setVisible(true));
        btnViewContent.addActionListener(e -> new ViewContentFrame().setVisible(true));
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}
