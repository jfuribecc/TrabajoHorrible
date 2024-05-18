import java.io.*;
import java.net.*;

public class ChatServer {
    private int port;
    private MediadorCasa mediator;

    public ChatServer(int port, MediadorCasa mediator) {
        this.port = port;
        this.mediator = mediator;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor de chat iniciado. Esperando conexión...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Cliente: " + inputLine);
                if (inputLine.equalsIgnoreCase("finalizar chat")) {
                    System.out.println("El cliente ha finalizado el chat.");
                    break;
                }
                if (inputLine.toLowerCase().startsWith("crear casa")) {
                    String[] parts = inputLine.split(" ");
                    if (parts.length == 5) {
                        String colorCasa = parts[2];
                        String colorTecho = parts[3];
                        String colorVentanas = parts[4];
                        mediator.addCommand(colorCasa, colorTecho, colorVentanas);
                    } else {
                        System.out.println("Comando incorrecto. Uso: crear casa <colorCasa> <colorTecho> <colorVentanas>");
                    }
                }
                System.out.print("Servidor: ");
                String userInput = stdIn.readLine();
                out.println(userInput);
                if (userInput.equalsIgnoreCase("finalizar chat")) {
                    System.out.println("Has finalizado el chat.");
                    break;
                }
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
