public class Musica implements Runnable {
    private String filePath;

    public Musica(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        // Lógica para la reproducción de la música
        Sonido sonido = new Sonido(filePath);
        sonido.play();
    }
}
