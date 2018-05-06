package elements;

public class AmbientLight {

    private Color _color;
    private double _ka;


    /**
     * @param c Color
     * @param ka The intensity of the color
     */
    public AmbientLight(Color c, double ka){
        this._color = c;
        this._ka = ka;
    }

    /**
     * @param c Color (From java)
     * @param ka The intensity of the color
     */
    public AmbientLight(java.awt.Color c, double ka){
        this._color = new Color(c);
        this._ka = ka;
    }
    /**
     * @return The intensity of the point I
     */
    public Color getIntensity() {
        Color c=new Color(_color); //We need to modify the original color - not optimal
        c.scale(_ka);
        return c;
    }
}
