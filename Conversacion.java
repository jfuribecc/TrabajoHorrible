public class Conversacion implements Runnable {
    private MediadorCasa mediator;

    public Conversacion(MediadorCasa mediator) {
        this.mediator = mediator;
    }

    @Override
    public void run() {
        // Iniciar servidor y cliente en diferentes hilos con GUI
        Thread serverThread = new Thread(() -> {
            new ChatServerGUI(12345, mediator);
        });

        Thread clientThread = new Thread(() -> {
            new ChatClientGUI("localhost", 12345);
        });

        serverThread.start();
        clientThread.start();

        try {
            serverThread.join();
            clientThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
