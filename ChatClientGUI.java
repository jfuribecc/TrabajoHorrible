import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClientGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private PrintWriter out;
    private Socket socket;

    public ChatClientGUI(String hostName, int port) {
        setTitle("Cliente de Chat");
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
                    chatArea.append("Cliente: " + message + "\n");
                    inputField.setText("");
                }
            }
        });

        setVisible(true);

        new Thread(() -> {
            try {
                socket = new Socket(hostName, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                chatArea.append("Conectado al servidor de chat.\n");

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    chatArea.append("Servidor: " + inputLine + "\n");
                    if (inputLine.equalsIgnoreCase("finalizar chat")) {
                        chatArea.append("El chat ha finalizado.\n");
                        closeConnection();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void closeConnection() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatClientGUI("localhost", 12345);
    }
}
