public class Main {
    public static void main(String[] args) {
        // Crear instancia del servidor y el cliente para la conversación
        Conversacion conversacion = new Conversacion();
        Thread threadConversacion = new Thread(conversacion);

        // Crear instancia de la clase Musica para la reproducción de música
        Musica musica = new Musica("Viva.wav");
        Thread threadMusica = new Thread(musica);

        // Crear instancia del Mediador para manejar la creación de casas
        MediadorCasa mediadorCasa = new MediadorCasa();
        Thread threadCasa = new Thread(mediadorCasa);

        // Iniciar los hilos
        threadConversacion.start();
        threadMusica.start();
        threadCasa.start();

        // Esperar a que los hilos terminen
        try {
            threadConversacion.join();
            threadMusica.join();
            threadCasa.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
