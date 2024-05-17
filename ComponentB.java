public class ComponentB extends Component {
    public ComponentB(Mediador mediator) {
        super(mediator);
    }

    public void reactOnB(Component sender) {
        System.out.println("ComponentB: Configurando color del techo.");
    }
}