package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FilePathPanel extends JPanel {
    File fileDir;

    public FilePathPanel() {
        super(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        //Components
        JPanel pathPanel = new JPanel(new BorderLayout());
        JTextField pathTextField = new JTextField();
        pathPanel.add(pathTextField);
        pathPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton fileChooserButton = new JButton();
        try {
            Image img = ImageIO.read(new File("assets/folderIconGray.png"));
            fileChooserButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            fileChooserButton.setText("...");
        }
        JButton downloadButton = new JButton("Download");

        buttonPanel.add(fileChooserButton);
        buttonPanel.add(downloadButton);

        //Listeners
        fileChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setDialogTitle("Choose path...");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    fileDir = fileChooser.getSelectedFile();
                    pathTextField.setText(fileDir.getAbsolutePath());
                }
                else {
                    fileDir = null;
                }
            }
        });

        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //execDownload();
            }
        });

        add(pathPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.LINE_END);
    }
}
