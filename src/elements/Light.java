package elements;

public abstract class Light {

    protected Color _color;

    /**
     * @param c Color
     */
    public Light(Color c) {
        this._color = c;
    }

    /**
     * Default Ctor
     */
    public Light() {
        this._color = new Color(255, 255, 255);
    }

    /**
     * @param c Color (From java)
     */
    public Light(java.awt.Color c) {
        this._color = new Color(c);
    }


    /**
     * Copy constructor
     *
     * @param l The light
     */
    public Light(Light l) {
        this._color = new Color(l._color);
    }


    /**
     * The light cTor
     *
     * @param r The red color
     * @param g The green color
     * @param b The blue color
     */
    public Light(int r, int g, int b) {
        this._color = new Color(r, g, b);
    }

    /**
     * @return The intensity of the point I
     */
    public Color getIntensity() {
        return _color;
    }
}
