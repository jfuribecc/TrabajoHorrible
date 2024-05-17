public class ComponentA extends Component {
    public ComponentA(Mediador mediator) {
        super(mediator);
    }

    public void operationA() {
        System.out.println("ComponentA: Crear casa.");
        mediator.notify(this, "crear casa");
    }
}
