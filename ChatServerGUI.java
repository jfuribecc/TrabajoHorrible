import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatServerGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private PrintWriter out;
    private MediadorCasa mediator;
    private Socket clientSocket;

    public ChatServerGUI(int port, MediadorCasa mediator) {
        this.mediator = mediator;
        setTitle("Servidor de Chat");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        inputField = new JTextField();
        add(inputField, BorderLayout.SOUTH);

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText();
                if (out != null && !message.isEmpty()) {
                    out.println(message);
                    chatArea.append("Servidor: " + message + "\n");
                    inputField.setText("");
                    processMessage(message);
                }
            }
        });

        setVisible(true);

        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                chatArea.append("Servidor de chat iniciado. Esperando conexión...\n");
                clientSocket = serverSocket.accept();
                chatArea.append("Cliente conectado.\n");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    chatArea.append("Cliente: " + inputLine + "\n");
                    processMessage(inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void processMessage(String message) {
        if (message.equalsIgnoreCase("finalizar chat")) {
            chatArea.append("El chat ha finalizado.\n");
            closeConnection();
        } else if (message.toLowerCase().startsWith("crear casa")) {
            String[] parts = message.split(" ");
            if (parts.length == 5) {
                String colorCasa = parts[2];
                String colorTecho = parts[3];
                String colorVentanas = parts[4];
                mediator.addCommand(colorCasa, colorTecho, colorVentanas);
            } else {
                chatArea.append("Comando incorrecto. Uso: crear casa <colorCasa> <colorTecho> <colorVentanas>\n");
            }
        }
    }

    private void closeConnection() {
        try {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MediadorCasa mediator = new MediadorCasa();
        new ChatServerGUI(12345, mediator);
    }
}
