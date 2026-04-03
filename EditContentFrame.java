package cms.gui;

import cms.dao.ContentDAO;
import cms.model.Content;

import javax.swing.*;
import java.awt.*;

public class EditContentFrame extends JFrame {

    private final JTextField idField;
    private final JTextField titleField;
    private final JTextArea bodyArea;
    private final JButton btnLoad;
    private final JButton btnUpdate;
    private final ContentDAO contentDAO = new ContentDAO();

    public EditContentFrame() {
        setTitle("Edit Content");
        setSize(620, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Main panel
        JPanel panel = new JPanel(new GridLayout(0, 1, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        idField = new JTextField();
        titleField = new JTextField();
        bodyArea = new JTextArea(8, 40);
        btnLoad = new JButton("Load");
        btnUpdate = new JButton("Update");

        // Top Row (Enter ID + Load)
        JPanel topRow = new JPanel(new BorderLayout(6, 6));
        topRow.add(new JLabel("Enter ID:"), BorderLayout.WEST);
        topRow.add(idField, BorderLayout.CENTER);
        topRow.add(btnLoad, BorderLayout.EAST);

        panel.add(topRow);
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Body:"));
        panel.add(new JScrollPane(bodyArea));
        panel.add(btnUpdate);

        add(panel, BorderLayout.CENTER);

        // Action for Load button
        btnLoad.addActionListener(e -> {
            String idText = idField.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Content ID.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(idText);
                Content content = contentDAO.getContentById(id);
                if (content != null) {
                    titleField.setText(content.getTitle());
                    bodyArea.setText(content.getBody());
                } else {
                    JOptionPane.showMessageDialog(this, "No content found for ID: " + id, "Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action for Update button
        btnUpdate.addActionListener(e -> {
            String idText = idField.getText().trim();
            String title = titleField.getText().trim();
            String body = bodyArea.getText().trim();

            if (idText.isEmpty() || title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(idText);
                boolean success = contentDAO.updateContent(id, title, body);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Content updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No content found or update failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EditContentFrame::new);
    }
}
