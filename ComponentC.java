public class ComponentC extends Component {
    public ComponentC(Mediador mediator) {
        super(mediator);
    }

    public void reactOnC(Component sender) {
        System.out.println("ComponentC: Configurando color de las ventanas.");
    }
}