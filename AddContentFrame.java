package cms.gui;

import cms.dao.ContentDAO;

import javax.swing.*;

public class AddContentFrame extends JFrame {

    private JTextField txtTitle;
    private JTextArea txtBody;
    private JButton btnSave;
    private JButton btnCancel;
    private final ContentDAO contentDAO = new ContentDAO();

    public AddContentFrame() {
        setTitle("Add Content");
        setSize(520, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(30, 30, 60, 25);
        add(lblTitle);

        txtTitle = new JTextField();
        txtTitle.setBounds(100, 30, 370, 25);
        add(txtTitle);

        JLabel lblBody = new JLabel("Body:");
        lblBody.setBounds(30, 70, 60, 25);
        add(lblBody);

        txtBody = new JTextArea();
        JScrollPane scroll = new JScrollPane(txtBody);
        scroll.setBounds(100, 70, 370, 240);
        add(scroll);

        btnSave = new JButton("Save");
        btnSave.setBounds(150, 330, 100, 30);
        add(btnSave);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(270, 330, 100, 30);
        add(btnCancel);

        btnCancel.addActionListener(e -> dispose());

        btnSave.addActionListener(e -> {
            String title = txtTitle.getText().trim();
            String body = txtBody.getText().trim();

            if (title.isEmpty() || body.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill title and body.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean saved = contentDAO.addContent(title, body);
            if (saved) {
                JOptionPane.showMessageDialog(this, "Content saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save content.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
