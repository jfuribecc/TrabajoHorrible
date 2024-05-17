public abstract class Component {
    protected Mediador mediator;

    public Component(Mediador mediator) {
        this.mediator = mediator;
    }
}
