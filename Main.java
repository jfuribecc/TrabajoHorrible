public class Main {
    public static void main(String[] args) {
        MediadorCasa mediadorCasa = new MediadorCasa();
        Thread threadCasa = new Thread(mediadorCasa);

        Conversacion conversacion = new Conversacion(mediadorCasa);
        Thread threadConversacion = new Thread(conversacion);

        Musica musica = new Musica("Viva.wav");
        Thread threadMusica = new Thread(musica);

        threadCasa.start();
        threadConversacion.start();
        threadMusica.start();

        // Esperar a que los hilos terminen
        try {
            threadCasa.join();
            threadConversacion.join();
            threadMusica.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
