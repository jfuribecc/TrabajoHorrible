public class ComponentD extends Component {
    private String colorCasa;

    public ComponentD(Mediador mediator) {
        super(mediator);
    }

    public void reactOnD(Component sender, String colorCasa) {
        System.out.println("ComponentD: Configurando color de la casa a " + colorCasa);
        this.colorCasa = colorCasa;
    }

    public String getColorCasa() {
        return colorCasa;
    }
}
