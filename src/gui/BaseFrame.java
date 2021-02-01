package gui;

import com.formdev.flatlaf.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import static utils.DataDownloadUtils.*;

public class BaseFrame extends JFrame {
    private static boolean dark = true;
    VideoDataPanel videoDataPanel;
    AudioDownloadPanel audioDownloadPanel;
    VideoDownloadPanel videoDownloadPanel;
    FilePathPanel filePathPanel;

    JTextField urlTextField;
    JTabbedPane tabbedPane;

    public BaseFrame() {
        //1.- Initial config
        setTitle("Youtube Downloader");
        setSize(700, 350);
        //setMinimumSize(new Dimension());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);
        try {
            if (dark) setIconImage(ImageIO.read(new File("assets/lightCloudDownloadIcon.png")));
            else setIconImage(ImageIO.read(new File("assets/darkCloudDownloadIcon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addJMenu();
        initPanels();

        //2.- Components
        JPanel urlPanel = new JPanel(); //Panel containing URL label and JTF
        urlPanel.setLayout(new BorderLayout());
        urlPanel.setBorder(BorderFactory.createEmptyBorder(7,5,5,5));

        JLabel urlLabel = new JLabel("Video URL: ");
        urlLabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        urlTextField = new JTextField();
        JPanel searchButtonPanel = new JPanel(new BorderLayout()); //Used because there's no gap between the JTF and the button
        searchButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
        JButton searchButton = new JButton("Search");
        searchButtonPanel.add(searchButton);
        searchButton.addActionListener(e -> {
            videoDataPanel.thumbnailImagePanel.setImage(getVideoThumbnailURL(urlTextField.getText()));
            String[] data = getVideoInfo(urlTextField.getText());
            videoDataPanel.videoTitleInfoLabel.setText(data[0]);
            videoDataPanel.videoLengthInfoLabel.setText(data[1]);
            JOptionPane.showMessageDialog(null, data[0]);
        });
        urlPanel.add(urlLabel, BorderLayout.LINE_START);
        urlPanel.add(urlTextField, BorderLayout.CENTER);
        urlPanel.add(searchButtonPanel, BorderLayout.LINE_END);


        JPanel videoPanel = new JPanel(); //Panel containing video download and info options
        videoPanel.setLayout(new GridLayout(1,2));

        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        tabbedPane.addTab("Audio Download", audioDownloadPanel);
        tabbedPane.addTab("Video Download", videoDownloadPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        videoPanel.add(tabbedPane);
        videoPanel.add(videoDataPanel);

        add(urlPanel, BorderLayout.NORTH);
        add(videoPanel, BorderLayout.CENTER);
        add(filePathPanel, BorderLayout.SOUTH);

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

        darkThemeMenuItem.addActionListener(e -> {
            dark = !dark;
            refreshUI();
        });

        settingsMenuItem.addActionListener(e -> {
            new SettingsDialog(this,"Settings", true);
        });

        exitMenuItem.addActionListener(e -> {
            dispose();
            System.exit(0);
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

            if (dark) setIconImage(ImageIO.read(new File("assets/lightCloudDownloadIcon.png")));
            else setIconImage(ImageIO.read(new File("assets/darkCloudDownloadIcon.png")));
        } catch (UnsupportedLookAndFeelException | IOException e) {
            e.printStackTrace();
        }
    }

    private void initPanels() { //TODO: actionListeners FROM HERE
        //Initialization
        audioDownloadPanel = new AudioDownloadPanel();
        videoDownloadPanel = new VideoDownloadPanel();
        videoDataPanel = new VideoDataPanel();
        videoDataPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 1)); //TODO
        filePathPanel = new FilePathPanel();

        //Listeners
        filePathPanel.downloadButton.addActionListener(e -> {
            System.out.println(urlTextField.getText());
            if (tabbedPane.getSelectedIndex() == 1) downloadBestVideo(urlTextField.getText(), filePathPanel.pathTextField.getText() + "/%(title)s.%(ext)s", "mp4");
            else downloadFormattedAudio(urlTextField.getText(), filePathPanel.pathTextField.getText() + "/%(title)s.%(ext)s", "wav");
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            UIManager.put("MenuItem.selectionType", "underline");
            UIManager.put("TextComponent.arc", 5);
            JFrame.setDefaultLookAndFeelDecorated( true );
            JDialog.setDefaultLookAndFeelDecorated( true );
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(BaseFrame::new);
    }

    public static boolean isDark() {
        return dark;
    }
}
