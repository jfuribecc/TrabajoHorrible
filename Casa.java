public class Casa {
    private String color;
    private String colorTecho;
    private String colorVentanas;

    public Casa(String color, String colorTecho, String colorVentanas) {
        this.color = color;
        this.colorTecho = colorTecho;
        this.colorVentanas = colorVentanas;
    }

    @Override
    public String toString() {
        return "Casa [color=" + color + ", colorTecho=" + colorTecho + ", colorVentanas=" + colorVentanas + "]";
    }
}
