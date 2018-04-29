package elements;

public class AmbientLight {

    private Color _color;
    private double _ka;


    public AmbientLight(Color c, double ka){
        this._color = c;
        this._ka = ka;
    }

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
