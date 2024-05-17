import java.util.HashMap;
import java.util.Map;

public class MediadorCasa implements Runnable, Mediador {
    private ComponentA componentA;
    private ComponentB componentB;
    private ComponentC componentC;
    private ComponentD componentD;
    private Map<String, Casa> casas;

    public MediadorCasa() {
        this.componentA = new ComponentA(this);
        this.componentB = new ComponentB(this);
        this.componentC = new ComponentC(this);
        this.componentD = new ComponentD(this);
        this.casas = new HashMap<>();
    }

    @Override
    public void run() {
        // Simulación de la lógica del Mediador
        componentA.operationA();
    }

    @Override
    public void notify(Component sender, String event) {
        if (event.equalsIgnoreCase("crear casa")) {
            componentB.reactOnB(sender);
            componentC.reactOnC(sender);
            componentD.reactOnD(sender);
        }
    }

    @Override
    public void createCasa(String colorCasa, String colorTecho, String colorVentanas) {
        Casa casa = new Casa(colorCasa, colorTecho, colorVentanas);
        casas.put(colorCasa, casa);
        System.out.println("Casa creada: " + casa);
    }
}
