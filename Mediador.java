public interface Mediador {
    void notify(Component sender, String event);
    void createCasa(String colorCasa, String colorTecho, String colorVentanas);
}
