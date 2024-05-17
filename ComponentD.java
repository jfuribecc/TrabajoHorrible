public class ComponentD extends Component {
    public ComponentD(Mediador mediator) {
        super(mediator);
    }

    public void reactOnD(Component sender) {
        System.out.println("ComponentD: Configurando color de la casa.");
    }
}