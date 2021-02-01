package gui;

import javax.swing.*;

import java.awt.*;

import static utils.DataDownloadUtils.*;

public class VideoDataPanel extends JPanel {
    JLabel videoTitleInfoLabel; //TODO: long video titles don't show up
    JLabel videoLengthInfoLabel;
    ImagePanel thumbnailImagePanel;

    public VideoDataPanel() {
        setLayout(new BorderLayout());

        JPanel thumbnailPanel = new JPanel(new BorderLayout());
        thumbnailPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        thumbnailImagePanel = new ImagePanel();
        thumbnailPanel.add(thumbnailImagePanel);

        JPanel infoPanel = new JPanel(new GridLayout(4,1));
        videoTitleInfoLabel = new JLabel("...");
        videoLengthInfoLabel = new JLabel("...");

        JLabel videoTitleLabel = new JLabel("Title:");
        JLabel videoLengthLabel = new JLabel("Length:");
        JPanel videoTitlePanel = new JPanel();
        JPanel videoLengthPanel = new JPanel();

        videoTitlePanel.add(videoTitleLabel);
        videoTitlePanel.add(videoTitleInfoLabel);

        videoLengthPanel.add(videoLengthLabel);
        videoLengthPanel.add(videoLengthInfoLabel);

        infoPanel.add(new JPanel());
        infoPanel.add(videoTitlePanel);
        infoPanel.add(videoLengthPanel);

        add(new JPanel(), BorderLayout.NORTH);
        add(thumbnailPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.SOUTH);
    }
}
