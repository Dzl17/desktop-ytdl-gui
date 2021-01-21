package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataDownloadUtils {
    private static final String INITIAL_CMD = "lib\\youtube_dl\\youtube-dl.exe --config-location lib\\youtube_dl ";

    /**
     * Returns the URL of the input video thumbnail.
     * @param videoURL URL of the video
     * @return Video thumbnail URL
     */
    public static String getVideoThumbnailURL(String videoURL) {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "lib\\youtube_dl\\youtube-dl.exe --get-thumbnail " + videoURL);
        pb.redirectErrorStream(true);
        Process process = null;
        try {
            process = pb.start();
        } catch (IOException ioException) {
            System.out.println("PB ERROR: " + ioException);
        }

        assert process != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())); //Read output
        try {
            return reader.readLine();
        } catch (IOException ioException) {
            System.out.println("LINE READING ERROR");
        }
        return null;
    }

    private enum AudioFormats { //TODO check valid values
        best, aac, flac, mp3, m4a, opus, vorbis, wav;
    }

    //TODO: general method using settings file
    public static void downloadFormattedAudio(String videoURL, String path, String format) {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c",
                INITIAL_CMD +
                "-x " +
                "--audio-format " + format + " " +
                "-o " + path + " " +
                videoURL);
        pb.redirectErrorStream(true);
        Process process = null;
        try {
            process = pb.start();
        } catch (IOException ioException) {
            System.out.println("PB ERROR: " + ioException);
        }

        assert process != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())); //Read output
        String line = null;
        while (true) {
            try {
                line = reader.readLine();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            if (line == null) { break; }
            System.out.println(line);
        }
    }

    public static void downloadBestVideo(String videoURL, String path, String format) {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c",
               INITIAL_CMD +
               "-f bestvideo[ext=mp4]+bestaudio[ext=m4a]/bestvideo+bestaudio " +
               //"-f bestvideo+bestaudio " +
               "-o " + path + " " +
               videoURL);
        pb.redirectErrorStream(true);
        Process process = null;
        try {
            process = pb.start();
        } catch (IOException ioException) {
            System.out.println("PB ERROR: " + ioException);
        }

        assert process != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())); //Read output
        String line = null;
        while (true) {
            try {
                line = reader.readLine();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            if (line == null) { break; }
            System.out.println(line);
        }
    }
}
