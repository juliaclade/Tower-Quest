package seng201.team0.audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Creates a new Thread playing the audio during the game.
 * @author ChatGPT (see README for link to chat)
 */
@SuppressWarnings("checkstyle:JavadocType")
public class Audio {

    /**
     * Default constructor for the Audio class.
     * Initializes the application and prepares necessary components.
     */
    public Audio() {
        super();
    }

    /**
     *  Creates a new Thread playing the audio during the game.
     * @param soundPath String path for the sound file.
     */
    public void playSound(final String soundPath) {
        new Thread(() -> {
            InputStream inputStream =
                    getClass().getResourceAsStream(soundPath);
            AudioInputStream audioInputStream;
            try {
                assert inputStream != null;
                audioInputStream = AudioSystem
                        .getAudioInputStream(
                                new BufferedInputStream(inputStream));
            } catch (UnsupportedAudioFileException | IOException e) {
                throw new RuntimeException(e);
            }
            Clip clip;
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
            try {
                clip.open(audioInputStream);
            } catch (LineUnavailableException | IOException e) {
                throw new RuntimeException(e);
            }
            clip.start();
            Clip finalClip = clip;
            AudioInputStream finalAudioInputStream = audioInputStream;
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    finalClip.close();
                    try {
                        finalAudioInputStream.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
        }).start();
    }
}
