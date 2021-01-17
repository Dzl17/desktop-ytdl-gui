package gui;

import com.formdev.flatlaf.*;

import javax.swing.*;

public class BaseFrame extends JFrame {
    public BaseFrame() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setTitle("Youtube downloader");
        setLocationRelativeTo(null);
        setSize(500, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BaseFrame();
            }
        });
    }
}
