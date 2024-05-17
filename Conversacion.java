public class Conversacion implements Runnable {
    @Override
    public void run() {
        // Iniciar servidor y cliente en diferentes hilos
        Thread serverThread = new Thread(() -> {
            ChatServer server = new ChatServer(12345);
            server.start();
        });
        
        Thread clientThread = new Thread(() -> {
            ChatClient client = new ChatClient("localhost", 12345);
            client.start();
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
