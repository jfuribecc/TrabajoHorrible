public class Musica implements Runnable {
    private String filePath;

    public Musica(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        Sonido sonido = new Sonido(filePath);
        sonido.play();
    }
}
