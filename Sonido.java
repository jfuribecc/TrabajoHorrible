import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.io.File;

public class Sonido {
    private String filePath;

    public Sonido(String filePath) {
        this.filePath = filePath;
    }

    public void play() {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            
            // Esperar a que el audio termine
            clip.drain();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
