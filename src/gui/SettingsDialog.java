package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static gui.BaseFrame.isDark;

public class SettingsDialog extends JDialog {
    public SettingsDialog(Frame owner, String title, boolean modal) {
        //1.- Initial config
        super(owner, title, modal);
        setSize(427, 300);
        setResizable(false);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);
        try {
            if (isDark()) setIconImage(ImageIO.read(new File("assets/settingsIcon.png"))); //TODO icons
            else setIconImage(ImageIO.read(new File("assets/darkCloudDownloadIcon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Setings tabs
        JTabbedPane settingsTabbedPane = new JTabbedPane();
        settingsTabbedPane.addTab("Video Selection", getVideoSelectionPanel()); //4
        settingsTabbedPane.addTab("Download", getDownloadPanel()); //3
        settingsTabbedPane.addTab("Filesystem", getFilesystemPanel()); //
        settingsTabbedPane.addTab("Post-Processing", getPostProcessingPanel());
        settingsTabbedPane.addTab("Other", getOtherSettingsPanel());


        JButton resetButton = new JButton("Reset"); //Rest settings TODO tooltip
        JPanel resetPanel = new JPanel();
        resetPanel.add(resetButton);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        JButton applyButton = new JButton("Apply");

        cancelButton.addActionListener(e -> { dispose(); });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(applyButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(resetPanel, BorderLayout.WEST);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        add(settingsTabbedPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel getVideoSelectionPanel() {
        JPanel panel = new JPanel(new GridLayout(4,1));
        panel.setVisible(true);

        JLabel maxFilesLabel = new JLabel("Maximum nº of files: "); //TODO tooltip
        JLabel maxFilesizeLabel = new JLabel("Maximum file size (mb): ");
        JLabel acceptPlaylistLabel = new JLabel("Download playlists: "); //TODO tooltip
        JLabel ageLimitLabel = new JLabel("Age limit (18+): ");

        JSpinner maxFilesSpinner = new JSpinner(new SpinnerNumberModel(1,0,100,1)); //TODO tooltip, 0 = no limit
        JSpinner maxFilesizeSpinner = new JSpinner(new SpinnerNumberModel(1, 0, 10000, 1)); //TODO same as above
        JCheckBox acceptPlaylistCheck = new JCheckBox();
        JCheckBox ageLimitCheck = new JCheckBox();

        JPanel maxFilesPanel = new JPanel();
        JPanel maxFilesizePanel = new JPanel();
        JPanel acceptPlaylistPanel = new JPanel();
        JPanel ageLimitPanel = new JPanel();

        maxFilesPanel.add(maxFilesLabel);
        maxFilesPanel.add(maxFilesSpinner);
        maxFilesizePanel.add(maxFilesizeLabel);
        maxFilesizePanel.add(maxFilesizeSpinner);
        acceptPlaylistPanel.add(acceptPlaylistLabel);
        acceptPlaylistPanel.add(acceptPlaylistCheck);
        ageLimitPanel.add(ageLimitLabel);
        ageLimitPanel.add(ageLimitCheck);

        panel.add(maxFilesPanel);
        panel.add(maxFilesizePanel);
        panel.add(acceptPlaylistPanel);
        panel.add(ageLimitPanel);
        return panel; //TODO improve
    }

    private JPanel getDownloadPanel() {
        JPanel panel = new JPanel();

        return panel;
    }

    private JPanel getFilesystemPanel() {
        JPanel panel = new JPanel();

        return panel;
    }

    private JPanel getPostProcessingPanel() {
        JPanel panel = new JPanel();

        return panel;
    }

    private JPanel getOtherSettingsPanel() {
        JPanel panel = new JPanel();

        return panel;
    }
}
