import java.io.*;
import java.net.*;

public class ChatClient {
    private String hostName;
    private int port;

    public ChatClient(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void start() {
        try (Socket socket = new Socket(hostName, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            
            String userInput;
            System.out.println("Conectado al servidor de chat.");
            
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                if (userInput.equalsIgnoreCase("finalizar chat")) {
                    System.out.println("Has finalizado el chat.");
                    break;
                }
                String serverResponse = in.readLine();
                if (serverResponse.equalsIgnoreCase("finalizar chat")) {
                    System.out.println("El servidor ha finalizado el chat.");
                    break;
                }
                System.out.println("Servidor: " + serverResponse);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
