import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.SwingUtilities;

public class MediadorCasa implements Runnable, Mediador {
    private ComponentA componentA;
    private ComponentB componentB;
    private ComponentC componentC;
    private ComponentD componentD;
    private BlockingQueue<String[]> commandQueue;

    public MediadorCasa() {
        this.componentA = new ComponentA(this);
        this.componentB = new ComponentB(this);
        this.componentC = new ComponentC(this);
        this.componentD = new ComponentD(this);
        this.commandQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String[] command = commandQueue.take();
                String colorCasa = command[0];
                String colorTecho = command[1];
                String colorVentanas = command[2];
                createCasa(colorCasa, colorTecho, colorVentanas);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    @Override
    public void notify(Component sender, String event) {
        // Este método se actualiza según sea necesario para manejar eventos
    }

    @Override
    public void createCasa(String colorCasa, String colorTecho, String colorVentanas) {
        componentD.reactOnD(null, colorCasa);
        componentB.reactOnB(null, colorTecho);
        componentC.reactOnC(null, colorVentanas);

        Casa casa = new Casa(componentD.getColorCasa(), componentB.getColorTecho(), componentC.getColorVentanas());
        System.out.println("Casa creada: " + casa);
        SwingUtilities.invokeLater(() -> new CasaGUI(componentD.getColorCasa(), componentB.getColorTecho(), componentC.getColorVentanas()));
    }

    public void addCommand(String colorCasa, String colorTecho, String colorVentanas) {
        try {
            commandQueue.put(new String[] { colorCasa, colorTecho, colorVentanas });
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

