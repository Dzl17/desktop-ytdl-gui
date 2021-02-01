package gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

import static utils.Utils.resizeBufferedImage;

public class ImagePanel extends JPanel{

    private Image image;

    public ImagePanel() {
        try {
            image = ImageIO.read(new URL("https://i.ytimg.com/vi/G1IbRujko-A/maxresdefault.jpg"));
            this.repaint();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getScaledInstance(220, -1, Image.SCALE_SMOOTH), 60, 0, this);
    }

    public void setImage(String url) {
        try {
            image = ImageIO.read(new URL(url));
            this.repaint();
        } catch (MalformedURLException malformedURLException) {
            JOptionPane.showMessageDialog(null, "Error: invalid URL. Please introduce a valid URL.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Invalid URL: " + malformedURLException);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
