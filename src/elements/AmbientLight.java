package elements;

public class AmbientLight extends Light {

    private double _ka;


    /**
     * This function is the Constructor for AmbientLight
     * @param c  Color
     * @param ka The intensity of the color
     */
    public AmbientLight(Color c, double ka) {
        super(c);
        this._ka = ka;
    }

    /**
     * This function is the constructor for awt color
     *
     * @param c  Color (From java)
     * @param ka The intensity of the color
     */
    public AmbientLight(java.awt.Color c, double ka) {
        super(c);
        this._ka = ka;
    }

    /**
     * This is the copy constructor
     * @param light
     */
    public AmbientLight(AmbientLight light) {
        super(light);
        this._ka = light._ka;
    }

    /**
     * @return The intensity of the point I
     */
    public Color getIntensity() {
        Color c = new Color(_color);
        return c.scale(_ka);
    }
}
