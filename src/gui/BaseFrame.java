package gui;

import com.formdev.flatlaf.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class BaseFrame extends JFrame {
    private static boolean dark = true;

    public BaseFrame() {
        //1.- Initial config
        setTitle("Youtube Downloader");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);
        addJMenu();

        //2.- Components
        JPanel urlPanel = new JPanel();
        urlPanel.setLayout(new BorderLayout());
        urlPanel.setBorder(BorderFactory.createEmptyBorder(7,5,5,5));

        JLabel urlLabel = new JLabel("Video URL: ");
        urlLabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        JTextField urlTextField = new JTextField();
        urlPanel.add(urlLabel, BorderLayout.LINE_START);
        urlPanel.add(urlTextField, BorderLayout.CENTER);

        JPanel dataDownloadPanel = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Audio Download", new AudioDownloadPanel());
        tabbedPane.addTab("Video Download", new VideoDownloadPanel());

        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        dataDownloadPanel.add(tabbedPane);

        add(urlPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        add(new FilePathPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addJMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu testMenu = new JMenu("File");
        JCheckBoxMenuItem darkThemeMenuItem = new JCheckBoxMenuItem("Dark theme");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        testMenu.add(darkThemeMenuItem);
        testMenu.add(exitMenuItem);
        menuBar.add(testMenu);

        setJMenuBar(menuBar);

        darkThemeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dark = !dark;
                refreshUI();
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    private void refreshUI() {
        try {
            if (isDark()) UIManager.setLookAndFeel(new FlatDarkLaf());
            else UIManager.setLookAndFeel(new FlatLightLaf());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            UIManager.put( "MenuItem.selectionType", "underline" );
            JFrame.setDefaultLookAndFeelDecorated( true );
            JDialog.setDefaultLookAndFeelDecorated( true );
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BaseFrame();
            }
        });
    }

    public static boolean isDark() {
        return dark;
    }
}
