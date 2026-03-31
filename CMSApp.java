package cms.gui;

import javax.swing.SwingUtilities;

public class CMSApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
