package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class AudioDownloadPanel extends JPanel {
    private final ButtonGroup formatButtonGroup;
    private final JSlider qualitySlider;

    public AudioDownloadPanel() {
        setLayout(new GridLayout(2,1));

        //File type
        formatButtonGroup = new ButtonGroup();
        JRadioButton aacRButton = new JRadioButton("aac");
        JRadioButton flacRButton = new JRadioButton("flac");
        JRadioButton mp3RButton = new JRadioButton("mp3");
        mp3RButton.setSelected(true);
        JRadioButton m4aRButton = new JRadioButton("m4a");
        JRadioButton opusRButton = new JRadioButton("opus");
        JRadioButton vorbisRButton = new JRadioButton("vorbis");
        JRadioButton wavRButton = new JRadioButton("wav");

        formatButtonGroup.add(aacRButton);
        formatButtonGroup.add(flacRButton);
        formatButtonGroup.add(mp3RButton);
        formatButtonGroup.add(m4aRButton);
        formatButtonGroup.add(opusRButton);
        formatButtonGroup.add(vorbisRButton);
        formatButtonGroup.add(wavRButton);

        JPanel fileTypePanel = new JPanel();
        fileTypePanel.setBorder(BorderFactory.createEmptyBorder(30,15,5,15));
        fileTypePanel.add(aacRButton);
        fileTypePanel.add(flacRButton);
        fileTypePanel.add(mp3RButton);
        fileTypePanel.add(m4aRButton);
        fileTypePanel.add(opusRButton);
        fileTypePanel.add(vorbisRButton);
        fileTypePanel.add(wavRButton);

        //Audio quality
        JPanel audioQualityPanel = new JPanel();
        audioQualityPanel.setBorder(BorderFactory.createEmptyBorder(20,0,5,0));
        JLabel qualityLabel = new JLabel("Quality:");
        qualitySlider = new JSlider(JSlider.HORIZONTAL, 0, 9, 0);
        qualitySlider.setPaintTrack(true);
        qualitySlider.setPaintTicks(true);
        qualitySlider.setPaintLabels(true);
        qualitySlider.setMajorTickSpacing(3);
        qualitySlider.setMinorTickSpacing(1);
        qualitySlider.setValue(5);

        audioQualityPanel.add(qualityLabel);
        audioQualityPanel.add(qualitySlider);

        add(fileTypePanel);
        add(audioQualityPanel);
    }

    /**
     * Returns the audio format of the selected JRadioButton.
     * @return Audio format string
     */
    public String getSelectedFormat() {
        Enumeration<AbstractButton> copyEnum = formatButtonGroup.getElements();
        while (copyEnum.hasMoreElements()) {
            AbstractButton nextButton = copyEnum.nextElement();
            if (nextButton.isSelected()) return nextButton.getText();
        }
        return "mp3";
    }

    public int getAudioQuality() {
        return qualitySlider.getValue();
    }
}
