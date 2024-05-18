public class ComponentC extends Component {
    private String colorVentanas;

    public ComponentC(Mediador mediator) {
        super(mediator);
    }

    public void reactOnC(Component sender, String colorVentanas) {
        System.out.println("ComponentC: Configurando color de las ventanas a " + colorVentanas);
        this.colorVentanas = colorVentanas;
    }

    public String getColorVentanas() {
        return colorVentanas;
    }
}
