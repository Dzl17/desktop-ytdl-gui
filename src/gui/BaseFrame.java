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
        //setMinimumSize(new Dimension());
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

        //File menu
        JMenu fileMenu = new JMenu("File");
        JCheckBoxMenuItem darkThemeMenuItem = new JCheckBoxMenuItem("Dark theme");
        JMenuItem settingsMenuItem = new JMenuItem("Settings");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(darkThemeMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(settingsMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        darkThemeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dark = !dark;
                refreshUI();
            }
        });

        settingsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });

        //About menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);

        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        setJMenuBar(menuBar);
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
