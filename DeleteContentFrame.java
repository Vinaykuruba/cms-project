package cms.gui;

import cms.dao.ContentDAO;

import javax.swing.*;
import java.awt.*;

public class DeleteContentFrame extends JFrame {

    private final JTextField txtID;
    private final JButton btnDelete;
    private final JButton btnCancel;
    private final ContentDAO contentDAO = new ContentDAO();

    public DeleteContentFrame() {
        setTitle("Delete Content");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridLayout(3, 1, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblID = new JLabel("Enter Content ID to Delete:");
        txtID = new JTextField();
        btnDelete = new JButton("Delete");
        btnCancel = new JButton("Cancel");

        panel.add(lblID);
        panel.add(txtID);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnPanel.add(btnDelete);
        btnPanel.add(btnCancel);
        panel.add(btnPanel);

        add(panel, BorderLayout.CENTER);

        // Action: Cancel button closes window
        btnCancel.addActionListener(e -> dispose());

        // Action: Delete button
        btnDelete.addActionListener(e -> {
            String idText = txtID.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a content ID.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(idText);
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete content with ID " + id + "?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean deleted = contentDAO.deleteContent(id);
                    if (deleted) {
                        JOptionPane.showMessageDialog(this, "Content deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        txtID.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "No content found with that ID.", "Not Found", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DeleteContentFrame::new);
    }
}
