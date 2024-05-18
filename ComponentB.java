public class ComponentB extends Component {
    private String colorTecho;

    public ComponentB(Mediador mediator) {
        super(mediator);
    }

    public void reactOnB(Component sender, String colorTecho) {
        System.out.println("ComponentB: Configurando color del techo a " + colorTecho);
        this.colorTecho = colorTecho;
    }

    public String getColorTecho() {
        return colorTecho;
    }
}
