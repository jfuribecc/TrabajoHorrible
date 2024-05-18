import javax.swing.*;
import java.awt.*;

public class CasaGUI extends JFrame {
    private String colorCasa;
    private String colorTecho;
    private String colorVentanas;

    public CasaGUI(String colorCasa, String colorTecho, String colorVentanas) {
        this.colorCasa = colorCasa;
        this.colorTecho = colorTecho;
        this.colorVentanas = colorVentanas;
        setTitle("Casa");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(getColorFromName(colorCasa));
        g.fillRect(100, 200, 200, 100); // cuerpo de la casa

        g.setColor(getColorFromName(colorTecho));
        int[] xPoints = {100, 200, 300};
        int[] yPoints = {200, 100, 200};
        g.fillPolygon(xPoints, yPoints, 3); // techo

        g.setColor(getColorFromName(colorVentanas));
        g.fillRect(120, 220, 40, 40); // ventana izquierda
        g.fillRect(240, 220, 40, 40); // ventana derecha
    }

    private Color getColorFromName(String colorName) {
        switch (colorName.toLowerCase()) {
            case "rojo":
                return Color.RED;
            case "azul":
                return Color.BLUE;
            case "verde":
                return Color.GREEN;
            default:
                return Color.BLACK; // color por defecto si no coincide con ninguno
        }
    }
}
